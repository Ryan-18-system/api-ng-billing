package dev.ryan.nobrega.application.strategy;

import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;

public class DebitoStrategy implements PaymentMethodStrategy{
    @Override
    public PaymentMethod getType() {
        return PaymentMethod.DEBITO;
    }

    @Override
    public void processar(BankTransactionDTO transaction) {

    }
}
