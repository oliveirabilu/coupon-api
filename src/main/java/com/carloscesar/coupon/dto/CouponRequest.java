package com.carloscesar.coupon.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponRequest(
        @NotBlank
        String code,
        @NotBlank
        String description,
        @NotNull
        @DecimalMin("0.5")
        BigDecimal discountValue,
        @NotNull
        @Future
        LocalDate expirationDate
) {
}
