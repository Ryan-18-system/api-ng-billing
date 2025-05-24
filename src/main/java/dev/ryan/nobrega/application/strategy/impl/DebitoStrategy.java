package dev.ryan.nobrega.application.strategy.impl;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.application.service.BankPercentageService;
import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.entities.Account;
import dev.ryan.nobrega.domain.model.entities.BankPercentage;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import dev.ryan.nobrega.utils.FinanceUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.logging.Logger;

@ApplicationScoped
public class DebitoStrategy implements PaymentMethodStrategy {
    private static final Logger LOG_DEBITO_STRATEGY = Logger.getLogger(DebitoStrategy.class.getName());
    private final BankPercentageService bankPercentageService;

    @Inject
    public DebitoStrategy(BankPercentageService bankPercentageService) {
        this.bankPercentageService = bankPercentageService;
    }

    @Override
    public PaymentMethod getType() {
        return PaymentMethod.D;
    }

    @Override
    public void process(Account account, BankTransactionDTO transaction) throws ApplicationServiceException {
        LOG_DEBITO_STRATEGY.info("Processando Débito");
        BankPercentage percentage = this.bankPercentageService.getBankPercentage(this.getType());
        BigDecimal rateTransaction = FinanceUtils.applyPercentage(transaction.getAmount(), percentage.getPercentageRate());
        BigDecimal totalPayment = transaction.getAmount().add(rateTransaction);
        account.subtract(totalPayment);
    }
}
