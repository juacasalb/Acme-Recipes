package acme.features.chef.pimpam;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamUpdateService implements AbstractUpdateService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository currencyRepository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		boolean result;
		int pimpamId;
		Pimpam pimpam;
		Chef chef;
		Item item;
		
		pimpamId = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(pimpamId);
		item = pimpam.getItem();
		chef = item.getChef();
		result = request.isPrincipal(chef);
		result = result && !item.getPublished();		
		
		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		this.repository.save(entity);
		
		request.bind(entity, errors, "title", "code", "description", "budget", "link", "finishingDate", "instantationMoment", "item.name");
		
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "finishingDate", "instantationMoment", "item.name");
		
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {

		assert request != null;
		
		Pimpam result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findPimpamById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("budget")) {
			
			
			final boolean availableCurrency = this.validateAvailableCurrency(entity.getBudget().toString().substring(2,5));
			errors.state(request, availableCurrency, "budget", "chef.pimpam.form.error.currency-not-available");

		}
	}

	@Override
	public void update(final Request<Pimpam> request, final Pimpam entity) {

		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	

	public boolean validateAvailableCurrency(final String currency) {

		final String currencies = this.currencyRepository.findAvailableCurrencies();
		final List<Object> listOfAvailableCurrencies = Arrays.asList((Object[]) currencies.split(","));

		return listOfAvailableCurrencies.contains(currency);
	}


	
	
	
	
}
