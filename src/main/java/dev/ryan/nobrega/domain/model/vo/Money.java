package dev.ryan.nobrega.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

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
    @Column(name = "cash", nullable = false)
    private BigDecimal cash;

    protected Money() {
    }

    public Money(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor deve ser positivo ou zero");
        }
        this.cash = valor.setScale(2, RoundingMode.HALF_UP);
    }

    public void somar(Money outro) {
        this.cash = this.cash.add(outro.cash);
    }

    public void subtrair(Money outro) {
        this.cash = this.cash.subtract(outro.cash);
    }

    public boolean isZero() {
        return BigDecimal.ZERO.compareTo(this.cash) == 0;
    }

    public BigDecimal getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return Objects.equals(getCash(), money.getCash());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCash());
    }
}
