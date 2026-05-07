package com.federation.mapper;

import com.federation.dto.request.PaymentRequest;
import com.federation.dto.response.MemberPaymentResponse;
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.MemberPayment;
import com.federation.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "accountCredited.id", target = "accountCreditedId")
    MemberPaymentResponse toDto(MemberPayment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Payment toEntity(PaymentRequest request);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "member.firstName", target = "memberName")
    @Mapping(source = "account.id", target = "accountId")
    PaymentResponse toResponse(Payment payment);
}
