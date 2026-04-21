package com.federation.mapper;

import com.federation.dto.request.PaymentRequest;
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "account", ignore = true)
    Payment toEntity(PaymentRequest request);

    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "memberName", expression = "java(payment.getMember().getFirstName() + \" \" + payment.getMember().getLastName())")
    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "accountName", source = "account.name")
    PaymentResponse toResponse(Payment payment);
}