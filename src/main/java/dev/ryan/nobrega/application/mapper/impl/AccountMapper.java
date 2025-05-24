package dev.ryan.nobrega.application.mapper.impl;

import dev.ryan.nobrega.application.exception.MapperException;
import dev.ryan.nobrega.application.mapper.Mapper;
import dev.ryan.nobrega.domain.model.dto.AccountDTO;
import dev.ryan.nobrega.domain.model.entities.Account;
import dev.ryan.nobrega.domain.model.vo.Money;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AccountMapper implements Mapper<Account, AccountDTO> {

    @Override
    public AccountDTO toDTO(Account entidade) {
        AccountDTO response = new AccountDTO();
        response.setAmount(entidade.getMoney().getAmount());
        response.setNumberAccount(entidade.getNumberAccount());
        return response;
    }

    @Override
    public Account toEntity(AccountDTO dto) {
        try {
            Account entity = new Account();
            entity.setNumberAccount(dto.getNumberAccount());
            Money amount = new Money(dto.getAmount());
            entity.setMoney(amount);
            return entity;
        } catch (IllegalStateException illEx) {
            throw new MapperException(illEx.getMessage(), illEx);
        }
    }
}
