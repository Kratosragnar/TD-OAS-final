package com.federation.dto.request;

import com.federation.enums.Frequency;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateMembershipFeeRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate eligibleFrom;

    @NotNull
    private Frequency frequency;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotBlank
    private String label;
}