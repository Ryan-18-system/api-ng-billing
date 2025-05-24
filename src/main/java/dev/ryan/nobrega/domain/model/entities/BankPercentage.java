package dev.ryan.nobrega.domain.model.entities;

import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import dev.ryan.nobrega.utils.ConstantesDB;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name = ConstantesDB.TB_BANK_PERCENTAGE, schema = ConstantesDB.DB_SCHEMA)
public class BankPercentage extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3957834216238533223L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_percentage_seq_gen")
    @SequenceGenerator(
            name = "bank_percentage_seq_gen",
            sequenceName = "ngbilling.bank_percentage_seq",
            allocationSize = 20
    )
    private Long id;
    @Column(name = "percentage_rate", precision = 5, scale = 4,nullable = false)
    private BigDecimal percentageRate;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method",nullable = false,unique = true)
    private PaymentMethod paymentMethod;

    public BankPercentage(Long id, BigDecimal percentageRate, PaymentMethod paymentMethod) {
        this.id = id;
        this.percentageRate = percentageRate;
        this.paymentMethod = paymentMethod;
    }

    public BankPercentage(BigDecimal percentageRate, PaymentMethod paymentMethod) {
        this.percentageRate = percentageRate;
        this.paymentMethod = paymentMethod;
    }

    public BankPercentage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPercentageRate() {
        return percentageRate;
    }

    public void setPercentageRate(BigDecimal percentageRate) {
        this.percentageRate = percentageRate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankPercentage that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPercentageRate(), that.getPercentageRate()) && paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPercentageRate(), paymentMethod);
    }
}
