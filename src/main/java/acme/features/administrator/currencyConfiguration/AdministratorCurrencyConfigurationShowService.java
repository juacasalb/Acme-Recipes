package acme.features.administrator.currencyConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.system.configuration.CurrencyConfiguration;
@Service
public class AdministratorCurrencyConfigurationShowService implements AbstractShowService<Administrator, CurrencyConfiguration>{

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	@Override
	public boolean authorise(final Request<CurrencyConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public CurrencyConfiguration findOne(final Request<CurrencyConfiguration> request) {
		assert request != null;
		
		CurrencyConfiguration result;
		
		result = this.repository.findCurrencyConfiguration();
		
		return result;
	}

	@Override
	public void unbind(final Request<CurrencyConfiguration> request, final CurrencyConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "defaultCurrency", "acceptedCurrencies");
	}
	
	
}
