package com.federation.service;

import com.federation.dto.request.CollectivityInformationRequest;
import com.federation.dto.request.CollectivityRequest;
import com.federation.dto.request.CreateMembershipFeeRequest;
import com.federation.dto.response.*;
import com.federation.entity.*;
import com.federation.enums.*;
import com.federation.exception.*;
import com.federation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectivityService {

    private final CollectivityRepository collectivityRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final MemberPaymentRepository memberPaymentRepository;

    @Transactional
    public List<CollectivityResponse> createCollectivities(List<CollectivityRequest> requests) {
        List<CollectivityResponse> responses = new ArrayList<>();
        for (CollectivityRequest req : requests) {
            if (req.getFederationApproval() == null || !req.getFederationApproval()) {
                throw new ValidationException("Autorisation de la fédération obligatoire");
            }
            if (req.getMembers() == null || req.getMembers().size() < 10) {
                throw new ValidationException("Au moins 10 membres sont requis");
            }
            LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
            List<Member> foundingMembers = memberRepository.findAllById(req.getMembers());
            long senior = foundingMembers.stream()
                    .filter(m -> m.getAdhesionDate() != null && !m.getAdhesionDate().isAfter(sixMonthsAgo))
                    .count();
            if (senior < 5) {
                throw new ValidationException("5 membres avec 6 mois d'ancienneté minimum exigés");
            }
            if (req.getStructure() == null) {
                throw new ValidationException("Structure de direction manquante");
            }
            Collectivity collectivity = new Collectivity();
            collectivity.setCity(req.getLocation());
            collectivity.setSpecialty(req.getSpecialty());
            collectivity.setCreationDate(LocalDate.now());
            collectivityRepository.save(collectivity);

            createMandate(collectivity, req.getStructure().getPresident(), MemberOccupation.PRESIDENT);
            createMandate(collectivity, req.getStructure().getVicePresident(), MemberOccupation.VICE_PRESIDENT);
            createMandate(collectivity, req.getStructure().getTreasurer(), MemberOccupation.TREASURER);
            createMandate(collectivity, req.getStructure().getSecretary(), MemberOccupation.SECRETARY);

            for (UUID id : req.getMembers()) {
                Member m = memberRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Membre non trouvé"));
                m.setCollectivity(collectivity);
                memberRepository.save(m);
            }
            responses.add(toCollectivityResponse(collectivity));
        }
        return responses;
    }

    private void createMandate(Collectivity c, UUID memberId, MemberOccupation role) {
        if (memberId == null) return;
        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Membre introuvable"));
        Mandate mandate = Mandate.builder()
                .member(m)
                .collectivity(c)
                .role(role)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .build();
        c.getMandates().add(mandate);
    }

    public CollectivityResponse getCollectivityById(UUID id) {
        Collectivity c = collectivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));
        return toCollectivityResponse(c);
    }

    @Transactional
    public CollectivityResponse updateInformations(UUID id, CollectivityInformationRequest request) {
        Collectivity c = collectivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));
        if (c.getNumber() != null || c.getName() != null) {
            throw new ValidationException("Informations déjà attribuées et immuables");
        }
        if (collectivityRepository.existsByNumber(request.getNumber())) {
            throw new ValidationException("Numéro déjà utilisé");
        }
        if (collectivityRepository.existsByName(request.getName())) {
            throw new ValidationException("Nom déjà utilisé");
        }
        c.setNumber(request.getNumber());
        c.setName(request.getName());
        collectivityRepository.save(c);
        return toCollectivityResponse(c);
    }

    public List<FinancialAccountResponse> getFinancialAccounts(UUID collectivityId, LocalDate at) {
        collectivityRepository.findById(collectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));
        List<Account> accounts = accountRepository.findByCollectivityId(collectivityId);
        return accounts.stream().map(acc -> mapAccountToResponse(acc, at)).collect(Collectors.toList());
    }

    public CollectivityStatsResponse getCollectivityStats(UUID collectivityId, LocalDate start, LocalDate end) {
        Collectivity c = collectivityRepository.findById(collectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));
        List<Member> members = memberRepository.findByCollectivityId(collectivityId);
        List<MembershipFee> activeFees = membershipFeeRepository.findByCollectivityIdAndStatus(collectivityId, ActivityStatus.ACTIVE);
        BigDecimal annualFee = activeFees.stream()
                .filter(f -> f.getFrequency() == Frequency.ANNUALLY)
                .map(MembershipFee::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<MemberPaymentStats> stats = members.stream().map(m -> {
            List<MemberPayment> payments = memberPaymentRepository.findByMemberIdAndCreationDateBetween(m.getId(), start, end);
            BigDecimal paid = payments.stream().map(MemberPayment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal unpaids = annualFee.subtract(paid);
            if (unpaids.compareTo(BigDecimal.ZERO) < 0) unpaids = BigDecimal.ZERO;
            MemberPaymentStats s = new MemberPaymentStats();
            s.setMemberId(m.getId());
            s.setMemberName(m.getFullName());
            s.setTotalPaid(paid);
            s.setUnpaids(unpaids);
            return s;
        }).collect(Collectors.toList());

        CollectivityStatsResponse resp = new CollectivityStatsResponse();
        resp.setCollectivityId(collectivityId);
        resp.setCollectivityName(c.getName());
        resp.setMemberStats(stats);
        return resp;
    }

    public FederationStatsResponse getFederationStats(LocalDate start, LocalDate end) {
        List<Collectivity> all = collectivityRepository.findAll();
        FederationStatsResponse resp = new FederationStatsResponse();
        resp.setPeriod(start + " / " + end);

        List<CollectivityUpToDateStat> upToDateStats = new ArrayList<>();
        List<CollectivityNewMembersStat> newMembersStats = new ArrayList<>();

        for (Collectivity c : all) {
            List<Member> members = memberRepository.findByCollectivityId(c.getId());
            List<MembershipFee> activeFees = membershipFeeRepository.findByCollectivityIdAndStatus(c.getId(), ActivityStatus.ACTIVE);
            BigDecimal annualFee = activeFees.stream()
                    .filter(f -> f.getFrequency() == Frequency.ANNUALLY)
                    .map(MembershipFee::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            long upToDateCount = members.stream().filter(m -> {
                List<MemberPayment> payments = memberPaymentRepository.findByMemberIdAndCreationDateBetween(m.getId(), start, end);
                BigDecimal paid = payments.stream().map(MemberPayment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                return paid.compareTo(annualFee) >= 0;
            }).count();
            double percent = members.isEmpty() ? 0 : (double) upToDateCount / members.size() * 100;

            CollectivityUpToDateStat u = new CollectivityUpToDateStat();
            u.setCollectivityId(c.getId());
            u.setCollectivityName(c.getName());
            u.setUpToDatePercentage(percent);
            upToDateStats.add(u);

            long newMembers = memberRepository.findNewMembersBetween(start, end).stream()
                    .filter(m -> m.getCollectivity() != null && m.getCollectivity().getId().equals(c.getId()))
                    .count();

            CollectivityNewMembersStat n = new CollectivityNewMembersStat();
            n.setCollectivityId(c.getId());
            n.setCollectivityName(c.getName());
            n.setNewMembersCount(newMembers);
            newMembersStats.add(n);
        }
        resp.setUpToDateStats(upToDateStats);
        resp.setNewMembersStats(newMembersStats);
        return resp;
    }

    private FinancialAccountResponse mapAccountToResponse(Account account, LocalDate at) {
        BigDecimal balance = account.getBalance();
        if (account instanceof CashAccount) {
            CashAccountResponse r = new CashAccountResponse();
            r.setId(account.getId());
            r.setAmount(balance);
            return r;
        } else if (account instanceof MobileBankingAccount ma) {
            MobileBankingAccountResponse r = new MobileBankingAccountResponse();
            r.setId(ma.getId());
            r.setAmount(balance);
            r.setHolderName(ma.getHolderName());
            r.setMobileBankingService(ma.getMobileBankingService().name());
            r.setMobileNumber(ma.getMobileNumber());
            return r;
        } else if (account instanceof BankAccount ba) {
            BankAccountResponse r = new BankAccountResponse();
            r.setId(ba.getId());
            r.setAmount(balance);
            r.setHolderName(ba.getHolderName());
            r.setBankName(ba.getBankName().name());
            r.setBankCode(ba.getBankCode());
            r.setBankBranchCode(ba.getBankBranchCode());
            r.setBankAccountNumber(ba.getBankAccountNumber());
            r.setBankAccountKey(ba.getBankAccountKey());
            return r;
        }
        throw new IllegalArgumentException("Type de compte inconnu");
    }

    private CollectivityResponse toCollectivityResponse(Collectivity c) {
        CollectivityResponse resp = new CollectivityResponse();
        resp.setId(c.getId());
        resp.setNumber(c.getNumber());
        resp.setName(c.getName());
        resp.setCity(c.getCity());
        resp.setSpecialty(c.getSpecialty());
        resp.setCreationDate(c.getCreationDate());
        resp.setValidatedByFederation(c.getValidatedByFederation());
        resp.setStatus(c.getStatus());

        List<MemberResponse> memberResponses = c.getMembers().stream()
                .map(this::toMemberResponse)
                .collect(Collectors.toList());
        resp.setMembers(memberResponses);

        CollectivityStructureResponse struct = new CollectivityStructureResponse();
        for (Mandate m : c.getMandates()) {
            MemberResponse dto = toMemberResponse(m.getMember());
            switch (m.getRole()) {
                case PRESIDENT -> struct.setPresident(dto);
                case VICE_PRESIDENT -> struct.setVicePresident(dto);
                case TREASURER -> struct.setTreasurer(dto);
                case SECRETARY -> struct.setSecretary(dto);
            }
        }
        resp.setStructure(struct);
        return resp;
    }

    private MemberResponse toMemberResponse(Member m) {
        MemberResponse dto = new MemberResponse();
        dto.setId(m.getId());
        dto.setFirstName(m.getFirstName());
        dto.setLastName(m.getLastName());
        dto.setFullName(m.getFullName());
        dto.setBirthDate(m.getBirthDate());
        dto.setGender(m.getGender());
        dto.setEmail(m.getEmail());
        dto.setPhone(m.getPhoneNumber());
        dto.setAddress(m.getAddress());
        dto.setJoinDate(m.getAdhesionDate());
        dto.setStatus(m.getStatus());
        if (m.getCollectivity() != null) {
            dto.setCollectivityId(m.getCollectivity().getId());
            dto.setCollectivityName(m.getCollectivity().getName());
        }
        if (m.getReferees() != null) {
            dto.setReferees(m.getReferees().stream().map(ref -> {
                SponsorResponse sp = new SponsorResponse();
                sp.setSponsorId(ref.getId());
                sp.setSponsorName(ref.getFullName());
                return sp;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
