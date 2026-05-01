package com.carloscesar.coupon.controller;

import com.carloscesar.coupon.domain.Coupon;
import com.carloscesar.coupon.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService couponService;
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @PostMapping
    public ResponseEntity<Coupon> create(@RequestBody Coupon coupon, UriComponentsBuilder uriBuilder){
        Coupon created = couponService.create(coupon);
        var uri=uriBuilder.path("/coupon/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        couponService.delete(id);
    return ResponseEntity.noContent().build();
    }

}
