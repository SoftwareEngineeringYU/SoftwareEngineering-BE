package com.example.sogong.infra.payment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class PortOneConfig {
    private final String apiId;
    private final String apiSecretKey;
    private final String identificationCode;
    private final String cid;

    public PortOneConfig(
            @Value("${app.portone.api-id}") String apiId,
            @Value("${app.portone.api-secret-key}") String apiSecretKey,
            @Value("${app.portone.id-code}") String identificationCode,
            @Value("${app.portone.cid}") String cid
    ) {
        this.apiId = apiId;
        this.apiSecretKey = apiSecretKey;
        this.identificationCode = identificationCode;
        this.cid = cid;
    }

}
