package acme.features.chef.ketema;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Ketema;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.features.chef.item.ChefItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefKetemaCreateService implements AbstractCreateService<Chef, Ketema>{

	@Autowired
	protected ChefKetemaRepository repository;
	
	@Autowired
	protected ChefItemRepository itemRepository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository currencyRepository;
	
	@Override
	public boolean authorise(final Request<Ketema> request) {

		assert request != null;
		
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void bind(final Request<Ketema> request, final Ketema entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		String code;
		moment = new Date(System.currentTimeMillis() - 1);
		code = "K3t3MA:" + ((moment.getMonth()+1)>9?moment.getMonth()+1:"0"+(moment.getMonth()+1)) + (moment.getYear()-100) + ":" + (moment.getDate()>9?moment.getDate():"0"+moment.getDate());

		code += "-" + request.getModel().getInteger("itemId");
		
		request.bind(entity, errors, "theme", "statement", "allotment", "moreInfo", "finishingDate");
		entity.setInstantationMoment(moment);
		entity.setKeylet(code);
	}

	@Override
	public void unbind(final Request<Ketema> request, final Ketema entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int chefId = this.repository.findChefById(request.getPrincipal().getActiveRoleId()).getId();
		final Collection<Item> items =  this.repository.findManyAvailableIngredientsByChef(chefId);
		
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "theme", "keylet", "statement", "allotment", "moreInfo", "finishingDate", "instantationMoment");
	}

	@Override
	public Ketema instantiate(final Request<Ketema> request) {

		assert request != null;
		
		Ketema ketema;
		
		ketema = new Ketema();
		
		return ketema;
	}

	@Override
	public void validate(final Request<Ketema> request, final Ketema entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
	
		
		if (!errors.hasErrors("allotment")) {

			final boolean availableCurrency = this.validateAvailableCurrency(entity.getAllotment().toString().substring(2,5));
			errors.state(request, availableCurrency, "allotment", "chef.ketema.form.error.currency-not-available");

		}
	}

	@Override
	public void create(final Request<Ketema> request, final Ketema entity) {

		assert request != null;
		assert entity != null;
		
		entity.setItem(this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
		
		this.repository.save(entity);
		
	}
	
	public boolean validateAvailableCurrency(final String currency) {

		final String currencies = this.currencyRepository.findAvailableCurrencies();
		final List<Object> listOfAvailableCurrencies = Arrays.asList((Object[]) currencies.split(","));

		return listOfAvailableCurrencies.contains(currency);
	}

	
}
