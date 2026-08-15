package com.pm.service;

import com.pm.payloads.response.PatientResponseDto;
import com.pm.payloads.response.request.PatientRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PatientService {
    List<PatientResponseDto> getAllPatient ();

    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);

    PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto);

     PatientResponseDto partialUpdate(UUID id, @Valid PatientRequestDto patientRequestDto);

      void delete(UUID id);
}
