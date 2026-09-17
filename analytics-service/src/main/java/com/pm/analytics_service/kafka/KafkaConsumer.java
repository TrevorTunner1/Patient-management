package com.pm.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
@Slf4j
public class KafkaConsumer  {

    @KafkaListener(topics = "patient",groupId = "analytics-service")
    public void kafkaConsumer (byte[] event){
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            log.info("Received patient event {}",patientEvent);
        } catch (InvalidProtocolBufferException e) {
            log.error("error consuming patient event {}",e.getMessage());
        }
    }
}

