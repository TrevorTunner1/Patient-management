package com.pm.configuration;

import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class GrpcClientConfig {
    @Value("${billing.service.address:localhost}")
    private String serverAddress;
    @Value("${billing.service.grpc.port:9001}")
    private int serverPort;

    

    @Bean
    public ManagedChannel billingServiceChannel (){
        return ManagedChannelBuilder.forAddress(serverAddress,serverPort).usePlaintext()
                .build();
    }

    @Bean
    public BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub(ManagedChannel billingServiceChannel ){
        return BillingServiceGrpc.newBlockingStub(billingServiceChannel);
    }
}
