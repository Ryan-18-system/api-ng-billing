package dev.ryan.nobrega.application.strategy.impl;

import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.logging.Logger;

@ApplicationScoped
@Named("credito_strategy")

public class CreditoStrategy implements PaymentMethodStrategy {
    private static final Logger LOG_CREDITO_STRATEGY = Logger.getLogger(CreditoStrategy.class.getName());

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.C;
    }

    @Override
    public void process(BankTransactionDTO transaction) {
        LOG_CREDITO_STRATEGY.info("Processando Crédito");
    }
}
