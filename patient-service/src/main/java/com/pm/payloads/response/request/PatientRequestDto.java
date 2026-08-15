package com.pm.payloads.response.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientRequestDto(
        @NotBlank String name,
        String address,
        @Email @NotBlank String email,
        @NotNull LocalDate dateOfBirth,
        @NotNull LocalDate registeredDate
) {}
