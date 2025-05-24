package dev.ryan.nobrega.application.strategy;

import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;

public interface PaymentMethodStrategy {
    PaymentMethod getType();
    void processar(BankTransactionDTO transaction);
}
