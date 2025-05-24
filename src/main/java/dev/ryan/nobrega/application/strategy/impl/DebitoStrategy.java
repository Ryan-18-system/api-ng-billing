package dev.ryan.nobrega.application.strategy.impl;

import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.logging.Logger;
@ApplicationScoped
public class DebitoStrategy implements PaymentMethodStrategy {
    private static final Logger LOG_DEBITO_STRATEGY = Logger.getLogger(DebitoStrategy.class.getName());
    @Override
    public PaymentMethod getType() {
        return PaymentMethod.D;
    }

    @Override
    public void process(BankTransactionDTO transaction) {
        LOG_DEBITO_STRATEGY.info("Processando Débito");
    }
}
