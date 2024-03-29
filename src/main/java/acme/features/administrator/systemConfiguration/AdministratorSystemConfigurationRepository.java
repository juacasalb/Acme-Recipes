package acme.features.administrator.systemConfiguration;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.CurrencyConfiguration;
import acme.system.configuration.SpamTuple;
@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {

	@Query("SELECT cc FROM CurrencyConfiguration cc")
	CurrencyConfiguration findCurrencyConfiguration();

	@Query("SELECT st FROM SpamTuple st")
	List<SpamTuple> findAllSpamTuple();
	
	@Query("select cc.acceptedCurrencies from CurrencyConfiguration cc")
	String findAvailableCurrencies();
	
	@Query("SELECT st FROM SpamTuple st WHERE st.id = :id")
	SpamTuple findSpamTupleById(int id);

}
