package com.pm.mapper;

import com.pm.modal.Patient;
import com.pm.payloads.response.PatientResponseDto;
import com.pm.payloads.response.request.PatientRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PatientMapper {
    public abstract PatientResponseDto toResponse(Patient patient);

    public abstract Patient toEntity(PatientRequestDto patientRequestDto);
}
