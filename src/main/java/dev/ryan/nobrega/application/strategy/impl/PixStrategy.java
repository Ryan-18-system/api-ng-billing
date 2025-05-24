package dev.ryan.nobrega.application.strategy.impl;

import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.logging.Logger;

@ApplicationScoped
@Named("pix_strategy")
public class PixStrategy implements PaymentMethodStrategy {
    private static final Logger LOG_PIX_STRATEGY = Logger.getLogger(PixStrategy.class.getName());

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.P;
    }

    @Override
    public void process(BankTransactionDTO transaction) {
        LOG_PIX_STRATEGY.info("Processando PIX");
    }
}
