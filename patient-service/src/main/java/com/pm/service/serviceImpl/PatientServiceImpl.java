package com.pm.service.serviceImpl;

import com.pm.modal.Patient;
import com.pm.payloads.response.PatientResponseDto;
import com.pm.repository.PatientRepository;
import com.pm.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDto> getAllPatient () {

         return   null;

    }
}
