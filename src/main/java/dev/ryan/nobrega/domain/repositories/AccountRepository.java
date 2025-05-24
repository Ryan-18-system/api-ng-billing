package dev.ryan.nobrega.domain.repositories;

import dev.ryan.nobrega.application.exception.ApplicationServiceException;
import dev.ryan.nobrega.domain.model.entities.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.Response;

import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {
    private static final Logger LOG_ACCOUNT_REPOSITORY = Logger.getLogger(AccountRepository.class.getName());

    public Account getAccountByNumber(Integer numberAccount) throws ApplicationServiceException {
        try {
            return find("numberAccount", numberAccount)
                    .singleResult();
        } catch (NoResultException noResultEx) {
            throw new ApplicationServiceException("no.result.account", Response.Status.NOT_FOUND.getStatusCode(), new String[]{numberAccount.toString()});
        } catch (Exception e) {
            LOG_ACCOUNT_REPOSITORY.severe(e.getMessage());
            throw new ApplicationServiceException("message.default");
        }
    }

    public Optional<Account> getAccountByNumberOpt(Integer numberAccount) throws ApplicationServiceException {
        try {
            return find("numberAccount", numberAccount).firstResultOptional();
        } catch (Exception e) {
            LOG_ACCOUNT_REPOSITORY.severe(e.getMessage());
            throw new ApplicationServiceException("message.default");
        }
    }
}
