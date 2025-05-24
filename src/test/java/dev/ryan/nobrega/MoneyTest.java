package dev.ryan.nobrega;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.domain.model.vo.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    void deveCriarInstanciaComValorValido() {
        Money money = new Money(BigDecimal.valueOf(100.00));
        assertEquals(BigDecimal.valueOf(100.00).setScale(2), money.getAmount());
    }

    @Test
    void naoDevePermitirValorNegativo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Money(BigDecimal.valueOf(-1))
        );
        assertEquals("Valor deve ser positivo ou zero", ex.getMessage());
    }

    @Test
    void deveSomarValoresCorretamente() {
        Money m1 = new Money(BigDecimal.valueOf(50.00));
        Money m2 = new Money(BigDecimal.valueOf(25.50));

        m1.add(m2);

        assertEquals(BigDecimal.valueOf(75.50).setScale(2), m1.getAmount());
    }

    @Test
    void deveSubtrairValoresCorretamente() throws ApplicationServiceException {
        Money m1 = new Money(BigDecimal.valueOf(100.00));
        Money m2 = new Money(BigDecimal.valueOf(40.00));

        m1.subtract(m2);

        assertEquals(BigDecimal.valueOf(60.00).setScale(2), m1.getAmount());
    }

    @Test
    void deveLancarExcecaoQuandoSubtracaoUltrapassarSaldo() {
        Money saldo = new Money(BigDecimal.valueOf(10.00));
        Money debito = new Money(BigDecimal.valueOf(20.00));

        ApplicationServiceException ex = assertThrows(ApplicationServiceException.class, () ->
                saldo.subtract(debito)
        );
        ApplicationServiceException exExpected = new ApplicationServiceException(
                "error.subtract.amount",
                422,
                new String[]{
                       debito.getAmount().toString(), saldo.getAmount().toString()
                }
        );
        assertEquals(422, ex.getStatusCode());
        assertEquals(exExpected.getMessage(), ex.getMessage());
    }

    @Test
    void deveCompararObjetosMoneyComoIguais() {
        Money m1 = new Money(BigDecimal.valueOf(30.00));
        Money m2 = new Money(BigDecimal.valueOf(30.00));

        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void deveDiferenciarObjetosMoneyComValoresDiferentes() {
        Money m1 = new Money(BigDecimal.valueOf(30.00));
        Money m2 = new Money(BigDecimal.valueOf(29.99));

        assertNotEquals(m1, m2);
    }
}
