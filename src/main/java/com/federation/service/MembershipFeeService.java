package com.federation.service;

import com.federation.dto.request.CreateMembershipFeeRequest;
import com.federation.dto.response.MembershipFeeResponse;
import com.federation.entity.*;
import com.federation.enums.ActivityStatus;
import com.federation.exception.*;
import com.federation.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipFeeService {

    private final MembershipFeeRepository feeRepository;
    private final CollectivityRepository collectivityRepository;

    @Transactional
    public List<MembershipFeeResponse> createFees(UUID collectivityId, List<CreateMembershipFeeRequest> requests) {
        Collectivity c = collectivityRepository.findById(collectivityId)
                .orElseThrow(() -> new ResourceNotFoundException("Collectivité non trouvée"));
        List<MembershipFee> fees = requests.stream().map(req -> MembershipFee.builder()
                .collectivity(c)
                .eligibleFrom(req.getEligibleFrom())
                .frequency(req.getFrequency())
                .amount(req.getAmount())
                .label(req.getLabel())
                .build()).collect(Collectors.toList());
        feeRepository.saveAll(fees);
        return fees.stream().map(f -> {
            MembershipFeeResponse r = new MembershipFeeResponse();
            r.setId(f.getId());
            r.setEligibleFrom(f.getEligibleFrom());
            r.setFrequency(f.getFrequency());
            r.setAmount(f.getAmount());
            r.setLabel(f.getLabel());
            r.setStatus(f.getStatus());
            return r;
        }).collect(Collectors.toList());
    }

    public List<MembershipFeeResponse> getFees(UUID collectivityId) {
        return feeRepository.findByCollectivityIdAndStatus(collectivityId, ActivityStatus.ACTIVE).stream().map(f -> {
            MembershipFeeResponse r = new MembershipFeeResponse();
            r.setId(f.getId());
            r.setEligibleFrom(f.getEligibleFrom());
            r.setFrequency(f.getFrequency());
            r.setAmount(f.getAmount());
            r.setLabel(f.getLabel());
            r.setStatus(f.getStatus());
            return r;
        }).collect(Collectors.toList());
    }
}
