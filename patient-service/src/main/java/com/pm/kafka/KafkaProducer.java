package com.pm.kafka;

import com.pm.modal.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendPatientEvent(Patient patient){
     PatientEvent event = PatientEvent
                .newBuilder()
             .setPatient(patient.getId().toString())
                .setEventType("PATIENT EVENT")
                .setEmail(patient.getEmail())
                .setName(patient.getName())
                .build();
        try{
            kafkaTemplate.send("patient",event.toByteArray());
        }catch (Exception e){
            log.error("Error sending PatientCreated event: {} {}",event,e.getMessage());
        }
    }
}

