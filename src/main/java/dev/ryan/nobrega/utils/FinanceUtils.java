package dev.ryan.nobrega.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanceUtils {

    public static BigDecimal applyPercentage(BigDecimal value, BigDecimal percentage) {
        return value.multiply(percentage.divide(BigDecimal.valueOf(100)))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
