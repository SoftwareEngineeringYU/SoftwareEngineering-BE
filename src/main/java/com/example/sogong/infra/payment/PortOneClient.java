package com.example.sogong.infra.payment;

import com.example.sogong.global.common.response.code.ErrorCode;
import com.example.sogong.global.common.response.exception.GlobalErrorException;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class PortOneClient implements PayClient {

    private final IamportClient iamportClient;


    public PortOneClient(PortOneConfig portOneConfig) {
        this.iamportClient = new IamportClient(portOneConfig.getApiId(), portOneConfig.getApiSecretKey());
    }

    @Override
    public void verify(String impUid, int amount, int orderPrice) {
        try {
            IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(impUid);
            log.debug("Payment: {}, {}, {}", iamportResponse.getCode(), iamportResponse.getMessage(), iamportResponse.getResponse());
            int actualPayAmount = iamportResponse.getResponse().getAmount().intValue();
            if (actualPayAmount != amount) {
                log.warn("actualPayAmount != amount");
                throw new GlobalErrorException(ErrorCode.PAYMENT_ERROR);
            }
            if (amount != orderPrice) {
                log.warn("mount != orderPrice");
                throw new GlobalErrorException(ErrorCode.PAYMENT_ERROR);
            }

        } catch (IOException | IamportResponseException e) {
            log.error("ERROR: {}", e.getMessage());
            throw new GlobalErrorException(ErrorCode.PAYMENT_ERROR);
        }

    }

}
