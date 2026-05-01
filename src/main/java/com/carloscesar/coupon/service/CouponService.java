package com.carloscesar.coupon.service;

import com.carloscesar.coupon.domain.Coupon;
import com.carloscesar.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    @Transactional
    public Coupon create(Coupon coupon){
        coupon.validate();
       return couponRepository.save(coupon);
    }
    @Transactional
    public void delete(UUID id){
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado"));

        coupon.delete();

        couponRepository.save(coupon);
    }
}
