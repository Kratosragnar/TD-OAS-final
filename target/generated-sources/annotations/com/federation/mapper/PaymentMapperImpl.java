package com.federation.mapper;

import com.federation.dto.request.PaymentRequest;
<<<<<<< HEAD
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.Account;
import com.federation.entity.Member;
=======
import com.federation.dto.response.MemberPaymentResponse;
import com.federation.dto.response.PaymentResponse;
import com.federation.entity.Account;
import com.federation.entity.Member;
import com.federation.entity.MemberPayment;
>>>>>>> d7e79cd (Fourth commit)
import com.federation.entity.Payment;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2026-04-21T15:18:55+0300",
=======
    date = "2026-05-07T12:50:48+0300",
>>>>>>> d7e79cd (Fourth commit)
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
<<<<<<< HEAD
=======
    public MemberPaymentResponse toDto(MemberPayment payment) {
        if ( payment == null ) {
            return null;
        }

        MemberPaymentResponse memberPaymentResponse = new MemberPaymentResponse();

        memberPaymentResponse.setAccountCreditedId( paymentAccountCreditedId( payment ) );
        memberPaymentResponse.setId( payment.getId() );
        memberPaymentResponse.setAmount( payment.getAmount() );
        memberPaymentResponse.setPaymentMode( payment.getPaymentMode() );
        memberPaymentResponse.setCreationDate( payment.getCreationDate() );

        return memberPaymentResponse;
    }

    @Override
>>>>>>> d7e79cd (Fourth commit)
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
<<<<<<< HEAD
        paymentResponse.accountId( paymentAccountId( payment ) );
        paymentResponse.accountName( paymentAccountName( payment ) );
=======
        paymentResponse.memberName( paymentMemberFirstName( payment ) );
        paymentResponse.accountId( paymentAccountId( payment ) );
>>>>>>> d7e79cd (Fourth commit)
        paymentResponse.id( payment.getId() );
        paymentResponse.amount( payment.getAmount() );
        paymentResponse.type( payment.getType() );
        paymentResponse.paymentDate( payment.getPaymentDate() );
        paymentResponse.reference( payment.getReference() );
        paymentResponse.description( payment.getDescription() );

<<<<<<< HEAD
        paymentResponse.memberName( payment.getMember().getFirstName() + " " + payment.getMember().getLastName() );

        return paymentResponse.build();
    }

=======
        return paymentResponse.build();
    }

    private UUID paymentAccountCreditedId(MemberPayment memberPayment) {
        if ( memberPayment == null ) {
            return null;
        }
        Account accountCredited = memberPayment.getAccountCredited();
        if ( accountCredited == null ) {
            return null;
        }
        UUID id = accountCredited.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

>>>>>>> d7e79cd (Fourth commit)
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

<<<<<<< HEAD
=======
    private String paymentMemberFirstName(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Member member = payment.getMember();
        if ( member == null ) {
            return null;
        }
        String firstName = member.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

>>>>>>> d7e79cd (Fourth commit)
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
<<<<<<< HEAD

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
=======
>>>>>>> d7e79cd (Fourth commit)
}
