package com.pm.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceGrpcClient {

    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(BillingServiceGrpc.BillingServiceBlockingStub blockingStub) {
        this.blockingStub = blockingStub;
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email){
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setEmail(email)
                .setName(name)
                .build();
        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received response from billing service via GRPC");
        return response;
    }
}