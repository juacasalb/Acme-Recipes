package acme.features.chef.item;

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
	
		request.bind(entity, errors, "name", "unit", "code", "description", "retailPrice", "link", "type", "published");
		
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
		final ItemType type = ItemType.valueOf((String) request.getModel().getAttribute("type"));
		item.setType(type);
		item.setChef(chef);
		
		return item;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("published")) {
			errors.state(request, request.getModel().getBoolean("published"), "published", "chef.item.published.required");
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
		
		final ItemType type = ItemType.valueOf((String) request.getModel().getAttribute("type"));
		entity.setType(type);
		entity.setPublished(false);
		
		this.repository.save(entity);
		
	}

	
}
