package dev.ryan.nobrega.domain.model.dto;

import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BankPercentageDTO(@NotNull @Min(0) BigDecimal amount, @NotNull PaymentMethod paymentMethod) {
}
