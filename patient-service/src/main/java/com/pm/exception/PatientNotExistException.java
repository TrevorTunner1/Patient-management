package com.pm.exception;

public class PatientNotExistException extends RuntimeException {
    public PatientNotExistException(String patientDoesNotExist) {
        super(patientDoesNotExist);
    }
}
