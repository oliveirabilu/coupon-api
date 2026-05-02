package com.carloscesar.coupon.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CouponTest {
    @Test
    void shouldThrowExceptionWhenCodeIsInvalid() {
        Coupon coupon = new Coupon(
                "A@1", // inválido (não vai dar 6 chars)
                "teste",
                BigDecimal.valueOf(10),
                LocalDate.now().plusDays(1)
        );

        assertThrows(IllegalArgumentException.class, coupon::validate);
    }
    @Test
    void shouldThrowExceptionWhenDiscountIsLessThanMinimum() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                BigDecimal.valueOf(0.1),
                LocalDate.now().plusDays(1)
        );

        assertThrows(IllegalArgumentException.class, coupon::validate);
    }
    @Test
    void shouldThrowExceptionWhenExpirationDateIsInThePast() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                BigDecimal.valueOf(10),
                LocalDate.now().minusDays(1)
        );

        assertThrows(IllegalArgumentException.class, coupon::validate);
    }
    @Test
    void shouldMarkCouponAsDeleted() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                BigDecimal.valueOf(10),
                LocalDate.now().plusDays(1)
        );

        coupon.delete();

        assertTrue(coupon.isDeleted());
    }
    @Test
    void shouldThrowExceptionWhenDeletingAlreadyDeletedCoupon() {
        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                BigDecimal.valueOf(10),
                LocalDate.now().plusDays(1)
        );

        coupon.delete();

        assertThrows(IllegalArgumentException.class, coupon::delete);
    }
}
