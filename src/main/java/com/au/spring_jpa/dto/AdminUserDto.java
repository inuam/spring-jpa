package com.au.spring_jpa.dto;

import jakarta.validation.constraints.NotBlank;

public class AdminUserDto extends UserDto {
    @NotBlank(message = "permission must not be blank")
    private String permissions;

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}