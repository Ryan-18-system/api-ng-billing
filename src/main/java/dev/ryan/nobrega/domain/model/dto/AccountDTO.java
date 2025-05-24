package dev.ryan.nobrega.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.ryan.nobrega.infra.serializer.MoneySerializer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AccountDTO {
    @JsonAlias("valor")
    @NotNull
    @Min(0)
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amount;
    @JsonAlias("numero_conta")
    @NotNull
    private Integer numberAccount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Integer numberAccount) {
        this.numberAccount = numberAccount;
    }
}
