package dev.ryan.nobrega.application.factory;

import dev.ryan.nobrega.application.strategy.PaymentMethodStrategy;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class PaymentMethodStrategyFactory {
    private final Map<PaymentMethod, PaymentMethodStrategy> storageStrategies;

    public PaymentMethodStrategyFactory( @Any Instance<PaymentMethodStrategy> paymentStrategyInstances) {
        this.storageStrategies = new HashMap<>();
        StreamSupport.stream(paymentStrategyInstances.spliterator(), false)
                .forEach(strategy -> {
                    PaymentMethod type = strategy.getType();
                    if (storageStrategies.containsKey(type)) {
                        throw new IllegalStateException("Atenção: Duas estratégias para o mesmo tipo de pagamento detectadas: " + type);
                    }
                    storageStrategies.put(type, strategy);
                });

        if (this.storageStrategies.isEmpty()) {
            throw new IllegalStateException("Atenção: Nenhuma PaymentMethodStrategy foi injetada no factory!");
        }
    }

    public PaymentMethodStrategy getStrategy(PaymentMethod tipo) {
        PaymentMethodStrategy strategy = storageStrategies.get(tipo);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipo);
        }
        return strategy;
    }
}
