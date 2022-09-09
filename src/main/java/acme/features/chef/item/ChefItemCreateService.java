package acme.features.chef.item;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefItemCreateService implements AbstractCreateService<Chef, Item>{

	@Autowired
	protected ChefItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublished(false);
		
		final Chef ch = this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		
		entity.setChef(ch);
		request.bind(entity, errors, "name", "unit", "code", "description", "retailPrice", "link", "type");
		
		System.out.println(ch);
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "unit", "code", "description", "retailPrice", "link", "type", "published");
	}

	@Override
	public Item instantiate(final Request<Item> request) {

		assert request != null;
		
		Item item;
		Chef chef;
		
		chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId());
		item = new Item();
		item.setChef(chef);
		item.setPublished(false);
		
		return item;
	}
	
	public boolean validateAvailableCurrencyRetailPrice(final Money retailPrice) {
		
		final String currencies = this.repository.findAvailableCurrencies();
		final List<Object> listOfAvailableCurrencies = Arrays.asList((Object[])currencies.split(";"));

		
		return listOfAvailableCurrencies.contains(retailPrice.getCurrency());		
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		System.out.println("errores: " + errors.getErroneousAttributes().toString());
		
		if (!errors.hasErrors("code")) {
			
			final Item existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.item.form.error.duplicated");

		}
		
		if(!errors.hasErrors("retailPrice")) {
			final Money retailPrice = entity.getRetailPrice();
			if (entity.getType().equals(ItemType.INGREDIENT)) {
				final boolean retailPriceComponentPositive = retailPrice.getAmount() > 0.;
				errors.state(request, retailPriceComponentPositive, "retailPrice", "chef.item.form.error.retail-price-ingredient-positive");

			} else if (entity.getType().equals(ItemType.KITCHENUTENSIL)) {
				final boolean retailPriceToolZeroOrPositive = retailPrice.getAmount() >= 0.;
				errors.state(request, retailPriceToolZeroOrPositive, "retailPrice", "chef.item.form.error.retail-price-kitchen-utensil-zero-or-positive");

			}
		}
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {

		assert request != null;
		assert entity != null;
		
		
		
		this.repository.save(entity);
		
	}

	
}
