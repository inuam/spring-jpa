package com.au.spring_jpa.dto;

import com.au.spring_jpa.entities.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,              // use logical names for subtypes
        include = JsonTypeInfo.As.PROPERTY,      // discriminator is a JSON property
        property = "userType",                    // 👈 discriminator property name
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleUserDto.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = SimpleUserDto.class, name = "AGENT"),
        @JsonSubTypes.Type(value = AdminUserDto.class, name = "ADMIN"),
        @JsonSubTypes.Type(value = CustomerUserDto.class, name = "CUSTOMER")
})
public abstract class UserDto {

    public UserDto() {}

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    @JsonProperty("userType")
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}