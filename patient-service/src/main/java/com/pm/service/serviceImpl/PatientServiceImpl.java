package com.pm.service.serviceImpl;

import com.pm.exception.EmailAlreadyExistException;
import com.pm.exception.PatientNotExistException;
import com.pm.grpc.BillingServiceGrpcClient;
import com.pm.kafka.KafkaProducer;
import com.pm.mapper.PatientMapper;
import com.pm.modal.Patient;
import com.pm.payloads.response.PatientResponseDto;
import com.pm.payloads.response.request.PatientRequestDto;
import com.pm.repository.PatientRepository;
import com.pm.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public List<PatientResponseDto> getAllPatient () {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream().map(patientMapper::toResponse).toList();
    }

    @Transactional
    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {

        if (patientRepository.existsByEmail(patientRequestDto.email())){
             throw new EmailAlreadyExistException("A patient with this email is already created "
                    + patientRequestDto.email());
        }

        Patient patient =patientRepository.save(patientMapper.toEntity(patientRequestDto));

        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(),patient.getName(),patient.getEmail());

        kafkaProducer.sendEvent(patient);

        return patientMapper.toResponse(patient);
    }

    @Override
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
       Patient patient =  patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotExistException("patient does not exist"));

        if (patientRepository.existsByEmail(patientRequestDto.email())){
            throw new EmailAlreadyExistException("A patient with this email is already created "
                    + patientRequestDto.email());
        }

        return patientMapper.toResponse(patientRepository.save(patientMapper.toEntity(patientRequestDto)));
    }

    @Override
    public PatientResponseDto partialUpdate(UUID id, PatientRequestDto patientRequestDto) {

        return patientRepository.findById(id).map(existingPat -> {
            Optional.ofNullable(patientRequestDto.address()).ifPresent(existingPat::setAddress);
            Optional.ofNullable(patientRequestDto.dateOfBirth()).ifPresent(existingPat::setDateOfBirth);
            Optional.ofNullable(patientRequestDto.name()).ifPresent(existingPat::setName);
            Optional.ofNullable(patientRequestDto.email()).ifPresent(existingPat::setEmail);
            return patientMapper.toResponse(existingPat);
        }).orElseThrow(()-> new PatientNotExistException("patient already exist"));
    }

    @Override
    public void delete(UUID id) {
         patientRepository.deleteById(id);
    }

}
