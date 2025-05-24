package dev.ryan.nobrega.domain.model.vo;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.ws.rs.core.Response;
import jdk.jshell.Snippet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Utilizando Padrão de Object Calisthenics
 * Para melhor desenvolvimento, evitar usar o Tipo BigDecimal diretamente na classe Account
 * 3. Wrap All Primitives And String
 */
@Embeddable
public class Money {
    @Column(name = "cash", nullable = false, columnDefinition = "numeric(10, 2)")
    private BigDecimal amount;

    protected Money() {
    }

    public Money(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor deve ser positivo ou zero");
        }
        this.amount = valor.setScale(2, RoundingMode.HALF_UP);
    }

    public void add(Money other) {
        this.amount = this.amount.add(other.amount);
    }

    public void subtract(Money other) throws ApplicationServiceException {
        if (this.amount.compareTo(other.amount) < 0) {
            throw new ApplicationServiceException("error.subtract.amount", 422, new String[]{other.amount.toString(), this.amount.toString()});
        }
        this.amount = this.amount.subtract(other.amount);
    }


    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return Objects.equals(getAmount(), money.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount());
    }
}
