package com.reginah.Expensetrackerapi.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

//@Data
public record  UserModel (
    @NotBlank(message = "Name should not be empty")
    String name,

    @NotNull(message = "Email should not be empty")
    @Column(unique = true)
    @Email(message = "Enter a valid email")
    String email,

    @NotNull(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be atleast 5 characters")
    String password,
    Long age //if the user does not provide age then it will automatically be set to zero
    ) { }
