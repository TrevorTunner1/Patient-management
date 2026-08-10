package com.pm.mapper;

import com.pm.modal.Patient;
import com.pm.payloads.response.PatientResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PatientMapper {
    protected abstract PatientResponseDto toResponse(Patient patient);
}
