package com.carloscesar.coupon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String code;
    private String description;
    private BigDecimal discountValue;
    private LocalDate expirationDate;
    private boolean deleted = false;

    public Coupon() {
    }

    public Coupon(UUID id, String code, String description,
                  BigDecimal discountValue, LocalDate expirationDate,
                  boolean deleted) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getDiscountValue() {
        return discountValue;
    }
    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public void normalizeCode() {
        if (this.code==null){
            throw new IllegalArgumentException("Código é obrigatório");
        }
        this.code = this.code.trim();
        this.code = this.code.replaceAll("[^a-zA-Z0-9]", "");
        this.code = this.code.toUpperCase();

        if (this.code.length() != 6) {
            throw new IllegalArgumentException("Código deve ter 6 caracteres alfanuméricos");
        }
    }

    public void validateDiscount() {
        if (this.discountValue==null){
            throw  new IllegalArgumentException("Desconto é obrigatório");
        }

        if (this.discountValue.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            throw new IllegalArgumentException("Desconto mínimo é 0.5");
        }
    }
    public void validateExpiration() {
        if (this.expirationDate==null){
            throw new IllegalArgumentException("Data de expiração é obrigatória");
        }
        if (this.expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de expiração não pode ser no passado");
        }
    }
    public void validate() {
        normalizeCode();
        validateDiscount();
        validateExpiration();
    }
}
