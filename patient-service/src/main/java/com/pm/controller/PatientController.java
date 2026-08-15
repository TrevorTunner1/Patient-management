package com.pm.controller;

import com.pm.payloads.response.PatientResponseDto;
import com.pm.payloads.response.request.PatientRequestDto;
import com.pm.service.PatientService;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatient (){
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createAPatient(@Valid @RequestBody PatientRequestDto patientRequestDto){
       return new ResponseEntity<>(patientService.createPatient(patientRequestDto),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @RequestBody @Validated(Builder.Default.class) PatientRequestDto patientRequestDto ){
        return new ResponseEntity<>(patientService.updatePatient(id,patientRequestDto),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponseDto> partialUpdatePatient(@PathVariable UUID id, @RequestBody @Valid  PatientRequestDto patientRequestDto){
        return new ResponseEntity<>(patientService.partialUpdate(id,patientRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable UUID id){
        patientService.delete(id);
         ResponseEntity.noContent().build();
    }
}
