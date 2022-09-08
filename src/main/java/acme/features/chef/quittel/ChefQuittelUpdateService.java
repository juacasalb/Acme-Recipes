package acme.features.chef.quittel;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Quittel;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefQuittelUpdateService implements AbstractUpdateService<Chef, Quittel>{

	@Autowired
	protected ChefQuittelRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository currencyRepository;
	
	@Override
	public boolean authorise(final Request<Quittel> request) {
		
		assert request != null;
		
		boolean result;
		int quittelId;
		Quittel quittel;
		Chef chef;
		Item item;
		
		quittelId = request.getModel().getInteger("id");
		quittel = this.repository.findQuittelById(quittelId);
		item = quittel.getItem();
		chef = item.getChef();
		result = request.isPrincipal(chef);
		result = result && !item.getPublished();		
		
		return result;
	}

	@Override
	public void bind(final Request<Quittel> request, final Quittel entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		this.repository.save(entity);
		
		request.bind(entity, errors, "title", "code", "description", "helping", "link", "finishingDate", "instantationMoment", "item.name");
		
	}

	@Override
	public void unbind(final Request<Quittel> request, final Quittel entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "helping", "link", "finishingDate", "instantationMoment", "item.name");
		
	}

	@Override
	public Quittel findOne(final Request<Quittel> request) {

		assert request != null;
		
		Quittel result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findQuittelById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Quittel> request, final Quittel entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("helping")) {
			
			
			final boolean availableCurrency = this.validateAvailableCurrency(entity.getHelping().toString().substring(2,5));
			errors.state(request, availableCurrency, "helping", "chef.quittel.form.error.currency-not-available");

		}
	}

	@Override
	public void update(final Request<Quittel> request, final Quittel entity) {

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
