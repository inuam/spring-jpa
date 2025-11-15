package com.au.spring_jpa.dto;

import jakarta.validation.constraints.Min;

public class CustomerUserDto extends UserDto {
    @Min(0)
    private Integer loyaltyPoints;

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}