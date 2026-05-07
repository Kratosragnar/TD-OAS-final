package com.federation.service;

import com.federation.dto.request.CreateMemberRequest;
import com.federation.dto.response.MemberResponse;
import com.federation.dto.response.SponsorResponse;
import com.federation.entity.*;
import com.federation.enums.MemberOccupation;
import com.federation.enums.MemberStatus;
import com.federation.exception.*;
import com.federation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CollectivityRepository collectivityRepository;

    @Transactional
    public List<MemberResponse> createMembers(List<CreateMemberRequest> requests) {
        List<MemberResponse> result = new ArrayList<>();
        for (CreateMemberRequest req : requests) {
            if (req.getReferees() == null || req.getReferees().size() < 2) {
                throw new ValidationException("Au moins 2 parrains sont requis");
            }
            Collectivity targetCollectivity = collectivityRepository.findById(req.getCollectivityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Collectivité cible non trouvée"));

            long sameCollectivityCount = req.getReferees().stream()
                    .map(s -> memberRepository.findById(s.getSponsorId())
                            .orElseThrow(() -> new ResourceNotFoundException("Parrain non trouvé")))
                    .filter(sponsor -> sponsor.getCollectivity() != null && sponsor.getCollectivity().getId().equals(targetCollectivity.getId()))
                    .count();

            if (sameCollectivityCount < req.getReferees().size() - sameCollectivityCount) {
                throw new ValidationException("Majorité des parrains doit appartenir à la collectivité cible");
            }

            Member member = Member.builder()
                    .firstName(req.getFirstName())
                    .lastName(req.getLastName())
                    .birthDate(req.getBirthDate())
                    .gender(req.getGender())
                    .address(req.getAddress())
                    .profession(req.getProfession())
                    .phoneNumber(req.getPhoneNumber())
                    .email(req.getEmail())
                    .occupation(MemberOccupation.JUNIOR)
                    .adhesionDate(LocalDate.now())
                    .status(MemberStatus.ACTIVE)
                    .collectivity(targetCollectivity)
                    .build();

            List<Member> referees = req.getReferees().stream()
                    .map(s -> memberRepository.findById(s.getSponsorId()).orElseThrow())
                    .collect(Collectors.toList());
            member.setReferees(referees);
            memberRepository.save(member);

            result.add(toMemberResponse(member));
        }
        return result;
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
