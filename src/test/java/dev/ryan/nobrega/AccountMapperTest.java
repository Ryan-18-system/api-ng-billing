package dev.ryan.nobrega;

import dev.ryan.nobrega.application.exception.MapperException;
import dev.ryan.nobrega.application.mapper.impl.AccountMapper;
import dev.ryan.nobrega.domain.model.dto.AccountDTO;
import dev.ryan.nobrega.domain.model.entities.Account;
import dev.ryan.nobrega.domain.model.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountMapperTest {
    private AccountMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AccountMapper();
    }

    @Test
    void deveConverterAccountParaAccountDTO() {
        Account conta = new Account();
        conta.setNumberAccount(12345);
        conta.setMoney(new Money(BigDecimal.valueOf(100.00)));

        AccountDTO dto = mapper.toDTO(conta);

        assertEquals(12345, dto.getNumberAccount());
        assertEquals(BigDecimal.valueOf(100.00).setScale(2), dto.getAmount());
    }

    @Test
    void deveConverterAccountDTOParaAccount() {
        AccountDTO dto = new AccountDTO();
        dto.setNumberAccount(67890);
        dto.setAmount(BigDecimal.valueOf(250.75));

        Account entity = mapper.toEntity(dto);

        assertEquals(67890, entity.getNumberAccount());
        assertEquals(BigDecimal.valueOf(250.75).setScale(2), entity.getMoney().getAmount());
    }

    @Test
    void deveLancarMapperExceptionSeValorForInvalido() {
        AccountDTO dto = new AccountDTO();
        dto.setNumberAccount(99999);
        dto.setAmount(BigDecimal.valueOf(-10));

        MapperException ex = assertThrows(MapperException.class, () -> mapper.toEntity(dto));
        assertEquals("Valor deve ser positivo ou zero", ex.getMessage());
    }
}
