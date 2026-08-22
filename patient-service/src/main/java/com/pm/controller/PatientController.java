package com.pm.controller;

import com.pm.payloads.response.PatientResponseDto;
import com.pm.payloads.response.request.PatientRequestDto;
import com.pm.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patient", description = "API for managing Patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient (){
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @PostMapping
    @Operation(summary = "Create a Patient")
    public ResponseEntity<PatientResponseDto> createAPatient(@Valid @RequestBody PatientRequestDto patientRequestDto){
       return new ResponseEntity<>(patientService.createPatient(patientRequestDto),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Full update a patient")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @RequestBody @Validated(Builder.Default.class) PatientRequestDto patientRequestDto ){
        return new ResponseEntity<>(patientService.updatePatient(id,patientRequestDto),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "partial update on a patient")
    public ResponseEntity<PatientResponseDto> partialUpdatePatient(@PathVariable UUID id, @RequestBody @Valid  PatientRequestDto patientRequestDto){
        return new ResponseEntity<>(patientService.partialUpdate(id,patientRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a patient ")
    public void deletePatient(@PathVariable UUID id){
        patientService.delete(id);
         ResponseEntity.noContent().build();
    }
}
