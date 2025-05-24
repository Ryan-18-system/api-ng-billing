package dev.ryan.nobrega.application.service;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.domain.model.dto.BankPercentageDTO;
import dev.ryan.nobrega.domain.model.entities.BankPercentage;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import dev.ryan.nobrega.domain.repositories.BankPercentageRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

@ApplicationScoped
public class BankPercentageService {
    private final BankPercentageRepository repository;

    @Inject
    public BankPercentageService(BankPercentageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BankPercentage createEntity(BankPercentageDTO dto) throws ApplicationServiceException {
        try {
            BankPercentage newBankPercentage = new BankPercentage(dto.amount(), dto.paymentMethod());
            this.repository.persist(newBankPercentage);
            return newBankPercentage;
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ApplicationServiceException("exist.payment.method",
                        Response.Status.CONFLICT.getStatusCode());
            }
            throw e;
        }
    }

    public BankPercentage getBankPercentage(PaymentMethod paymentMethod) throws ApplicationServiceException {
        return this.repository.getBankPercentage(paymentMethod);
    }
}
