package dev.ryan.nobrega.application.service;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.application.exception.MapperException;
import dev.ryan.nobrega.application.factory.PaymentMethodStrategyFactory;
import dev.ryan.nobrega.application.mapper.impl.AccountMapper;
import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.dto.AccountDTO;
import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.entities.Account;
import dev.ryan.nobrega.domain.repositories.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class AccountService {
    private final PaymentMethodStrategyFactory strategyFactory;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    @Inject
    public AccountService(PaymentMethodStrategyFactory strategyFactory, AccountMapper accountMapper, AccountRepository accountRepository) {
        this.strategyFactory = strategyFactory;
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    public AccountDTO processBankTransaction(BankTransactionDTO transaction) throws ApplicationServiceException {
        PaymentMethodStrategy strategy = strategyFactory.getStrategy(transaction.getPaymentType());
        strategy.process(transaction);
        return new AccountDTO();
    }

    @Transactional
    public AccountDTO createBankAccount(AccountDTO dto) throws ApplicationServiceException, MapperException {
        Optional<Account> accountAux = this.accountRepository.getAccountByNumberOpt(dto.getNumberAccount());
        if (accountAux.isPresent()) {
            throw new ApplicationServiceException("exist.account",
                    Response.Status.CONFLICT.getStatusCode(),
                    new String[]{dto.getNumberAccount().toString()});
        }
        Account newAccount = accountMapper.toEntity(dto);
        this.accountRepository.persist(newAccount);
        return accountMapper.toDTO(newAccount);
    }
}
