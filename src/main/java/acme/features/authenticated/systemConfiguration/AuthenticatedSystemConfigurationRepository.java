package acme.features.authenticated.systemConfiguration;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.CurrencyConfiguration;
import acme.system.configuration.SpamTuple;

@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository{

	@Query("SELECT cc FROM CurrencyConfiguration cc")
	CurrencyConfiguration findCurrencyConfiguration();

	@Query("SELECT st FROM SpamTuple st")
	List<SpamTuple> findAllSpamTuple();
}
