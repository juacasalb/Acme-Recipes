package acme.features.administrator.currencyConfiguration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;
import acme.system.configuration.CurrencyConfiguration;
@Service
public class AdministratorCurrencyConfigurationUpdateService implements AbstractUpdateService<Administrator, CurrencyConfiguration>{

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
	
	@Override
	public void bind(final Request<CurrencyConfiguration> request, final CurrencyConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "defaultCurrency", "acceptedCurrencies");
	}
	
	@Override
	public void validate(final Request<CurrencyConfiguration> request, final CurrencyConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("defaultCurrency")) {

			final boolean availableCurrency = this.validateAvailableCurrency(entity.getDefaultCurrency());
			errors.state(request, availableCurrency, "defaultCurrency", "administrator.currencyConfiguration.form.error.currency-not-available");

		}
	}

	@Override
	public void update(final Request<CurrencyConfiguration> request, final CurrencyConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	public boolean validateAvailableCurrency(final String currency) {

		final String currencies = this.repository.findAvailableCurrencies();
		final List<Object> listOfAvailableCurrencies = Arrays.asList((Object[]) currencies.split(","));

		return listOfAvailableCurrencies.contains(currency);
	}

}
