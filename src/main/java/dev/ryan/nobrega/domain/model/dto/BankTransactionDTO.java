package dev.ryan.nobrega.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;

import java.math.BigDecimal;

public class BankTransactionDTO {
    @JsonAlias("forma_pagamento")
    private PaymentMethod paymentType;

    @JsonAlias("valor")

    private BigDecimal amount;
    @JsonAlias("numero_conta")
    private Integer numberAccount;

    public PaymentMethod getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethod paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Integer numberAccount) {
        this.numberAccount = numberAccount;
    }
}
