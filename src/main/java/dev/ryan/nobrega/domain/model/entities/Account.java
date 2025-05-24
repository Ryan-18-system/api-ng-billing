package dev.ryan.nobrega.domain.model.entities;


import dev.ryan.nobrega.domain.model.vo.Money;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

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
