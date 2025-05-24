package dev.ryan.nobrega.domain.model.entities;


import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.domain.model.vo.Money;
import jakarta.persistence.*;
import jakarta.ws.rs.core.Response;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static dev.ryan.nobrega.utils.ConstantesDB.DB_SCHEMA;
import static dev.ryan.nobrega.utils.ConstantesDB.TB_ACCOUNT;

@Entity
@Table(name = TB_ACCOUNT, schema = DB_SCHEMA)
public class Account extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1873944901676974563L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
    @SequenceGenerator(
            name = "account_seq_gen",            // nome interno do generator (JPA)
            sequenceName = "ngbilling.account_seq",        // nome real da sequence no banco
            allocationSize = 20
    )
    private Long id;
    @Column(name = "number_account")
    private Integer numberAccount;

    @Embedded
    private Money money;

    public Account() {
    }

    public void add(BigDecimal value) throws ApplicationServiceException {
        try {
            Money amountToSubtract = new Money(value);
            this.money.add(amountToSubtract);
        } catch (IllegalArgumentException e) {
            throw new ApplicationServiceException(
                    "valor.insuficiente",
                    422,
                    new String[]{value.toPlainString()}
            );
        }
    }

    public void subtract(BigDecimal value) throws ApplicationServiceException {
        try {
            Money amountToSubtract = new Money(value);
            this.money.subtract(amountToSubtract);
        } catch (IllegalArgumentException e) {
            throw new ApplicationServiceException(
                    "valor.insuficiente",
                    422,
                    new String[]{value.toPlainString()}
            );
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Integer numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
