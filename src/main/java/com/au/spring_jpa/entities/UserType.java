package com.au.spring_jpa.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    ADMIN, CUSTOMER, SIMPLE, AGENT;

    @JsonCreator
    public static UserType fromString(String value) {
        return value == null ? null : UserType.valueOf(value.toUpperCase());
    }
}