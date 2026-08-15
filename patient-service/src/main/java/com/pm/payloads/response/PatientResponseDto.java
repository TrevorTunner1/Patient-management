package com.pm.payloads.response;

import java.time.LocalDate;
import java.util.UUID;

public record  PatientResponseDto(UUID id, String name, String email, String address, String dateOfBirth,
                                  LocalDate registeredDate) {
}
