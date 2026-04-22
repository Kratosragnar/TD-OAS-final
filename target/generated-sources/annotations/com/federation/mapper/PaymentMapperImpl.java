package com.federation.mapper;

import com.federation.dto.request.PaymentRequest;
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.Account;
import com.federation.entity.Member;
import com.federation.entity.Payment;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-22T16:54:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toEntity(PaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.amount( request.getAmount() );
        payment.type( request.getType() );
        payment.paymentDate( request.getPaymentDate() );
        payment.reference( request.getReference() );
        payment.description( request.getDescription() );

        return payment.build();
    }

    @Override
    public PaymentResponse toResponse(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponse.PaymentResponseBuilder paymentResponse = PaymentResponse.builder();

        paymentResponse.memberId( paymentMemberId( payment ) );
        paymentResponse.accountId( paymentAccountId( payment ) );
        paymentResponse.accountName( paymentAccountName( payment ) );
        paymentResponse.id( payment.getId() );
        paymentResponse.amount( payment.getAmount() );
        paymentResponse.type( payment.getType() );
        paymentResponse.paymentDate( payment.getPaymentDate() );
        paymentResponse.reference( payment.getReference() );
        paymentResponse.description( payment.getDescription() );

        paymentResponse.memberName( payment.getMember().getFirstName() + " " + payment.getMember().getLastName() );

        return paymentResponse.build();
    }

    private UUID paymentMemberId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Member member = payment.getMember();
        if ( member == null ) {
            return null;
        }
        UUID id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID paymentAccountId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Account account = payment.getAccount();
        if ( account == null ) {
            return null;
        }
        UUID id = account.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String paymentAccountName(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Account account = payment.getAccount();
        if ( account == null ) {
            return null;
        }
        String name = account.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
