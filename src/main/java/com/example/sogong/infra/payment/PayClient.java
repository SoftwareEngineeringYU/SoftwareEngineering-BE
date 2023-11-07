package com.example.sogong.infra.payment;

public interface PayClient {

    void verify(String impUid, int amount, int orderPrice);

}
