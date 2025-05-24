package dev.ryan.nobrega.application.strategy;

import dev.ryan.nobrega.domain.model.dto.BankTransactionDTO;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;

/**
 * https://refactoring.guru/design-patterns/strategy
 *  aplicando o padrão strategy para definir em tempo de exceução qual estratégia de pagamento será executada
 */
public interface PaymentMethodStrategy {
    PaymentMethod getType();
    void process(BankTransactionDTO transaction);
}
