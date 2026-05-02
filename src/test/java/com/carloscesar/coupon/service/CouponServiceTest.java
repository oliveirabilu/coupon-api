package com.carloscesar.coupon.service;

import com.carloscesar.coupon.domain.Coupon;
import com.carloscesar.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CouponServiceTest {

    @Test
    void shouldCreateCoupon() {
        CouponRepository repository = mock(CouponRepository.class);
        CouponService service = new CouponService(repository);

        Coupon coupon = new Coupon(
                "ABC123",
                "teste",
                BigDecimal.valueOf(10),
                LocalDate.now().plusDays(1)
        );

        when(repository.save(any(Coupon.class))).thenReturn(coupon);

        Coupon result = service.create(coupon);

        assertNotNull(result);
        verify(repository, times(1)).save(any(Coupon.class));
    }
    @Test
    void shouldThrowExceptionWhenCouponNotFound() {
        CouponRepository repository = mock(CouponRepository.class);
        CouponService service = new CouponService(repository);

        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.delete(id));
    }
}
