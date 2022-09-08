package acme.features.chef.quittel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Quittel;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.features.chef.item.ChefItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuittelCreateService implements AbstractCreateService<Chef, Quittel>{

	@Autowired
	protected ChefQuittelRepository repository;
	
	@Autowired
	protected ChefItemRepository itemRepository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository currencyRepository;
	
	@Override
	public boolean authorise(final Request<Quittel> request) {

		assert request != null;
		
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void bind(final Request<Quittel> request, final Quittel entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		String code;
		String fecha;
		moment = new Date(System.currentTimeMillis() - 1);
		fecha = moment.getYear()-100 + "-" + ((moment.getMonth()+1)>9?moment.getMonth()+1:"0"+(moment.getMonth()+1)) + "-" +  (moment.getDate()>9?moment.getDate():"0"+moment.getDate());

		code = "A3A" + request.getModel().getInteger("itemId") + "-" + fecha;
		
		request.bind(entity, errors, "title", "description", "helping", "link", "finishingDate");
		entity.setInstantationMoment(moment);
		entity.setCode(code);
	}

	@Override
	public void unbind(final Request<Quittel> request, final Quittel entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		Principal principal;

		principal = request.getPrincipal();
		
//		//si es ingredient
//		final Collection<Item> li = this.repository.findIngredientsByChefId(principal.getActiveRoleId());
//		model.setAttribute("items", li);
				
		//si es component
		final Collection<Item> li = this.repository.findKitchenUtensilsByChefId(principal.getActiveRoleId());
		model.setAttribute("items", li);
		
		request.unbind(entity, model, "title", "code", "description", "helping", "link", "finishingDate", "instantationMoment");
	}

	@Override
	public Quittel instantiate(final Request<Quittel> request) {

		assert request != null;
		
		Quittel quittel;
		
		quittel = new Quittel();
		
		return quittel;
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
	public void create(final Request<Quittel> request, final Quittel entity) {

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
