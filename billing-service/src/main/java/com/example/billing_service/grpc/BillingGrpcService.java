package com.example.billing_service.grpc;

import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase{

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> billingResponseStreamObserver){
        try{
            log.info("  request received {}", billingRequest.toString());

            billing.BillingResponse response = billing.BillingResponse.newBuilder()
                    .setAccountId("12345")
                    .setStatus("ACTIVE")
                    .build();

            billingResponseStreamObserver.onNext(response);
            billingResponseStreamObserver.onCompleted();
        }catch (Exception e){
            billingResponseStreamObserver.onError(
                    io.grpc.Status.INTERNAL
                            .withDescription("Failed to create billing account: " + e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}
