package dev.ryan.nobrega.domain.repositories;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.domain.model.entities.Account;
import dev.ryan.nobrega.domain.model.entities.BankPercentage;
import dev.ryan.nobrega.domain.model.enums.PaymentMethod;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class BankPercentageRepository implements PanacheRepository<BankPercentage> {
    public BankPercentage getBankPercentage(PaymentMethod paymentMethod) throws ApplicationServiceException {
        try {
            return find("paymentMethod", paymentMethod)
                    .singleResult();
        } catch (NoResultException noResultEx) {
            throw new ApplicationServiceException("no.result.percentage.payment.method", Response.Status.NOT_FOUND.getStatusCode(), new String[]{paymentMethod.getDescricao()});
        } catch (Exception e) {
            throw new ApplicationServiceException("message.default");
        }
    }
}
