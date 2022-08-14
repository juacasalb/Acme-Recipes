package acme.features.chef.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefItemUpdateService implements AbstractUpdateService<Chef, Item>{

	@Autowired
	protected ChefItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		
		assert request != null;
		
		boolean result;
		int id;
		Item item;
		Chef chef;
		
		id = request.getModel().getInteger("id");
		item = this.repository.findItemById(id);
		chef = item.getChef();
		result = !item.getPublished() && request.isPrincipal(chef);
		
		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPublished(false);
		this.repository.save(entity);
		
		request.bind(entity, errors, "name", "unit", "code", "description", "retailPrice", "link", "type");
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "unit", "code", "description", "retailPrice", "link", "type", "published");
		
	}

	@Override
	public Item findOne(final Request<Item> request) {

		assert request != null;
		
		Item result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);
		
		return result;
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
	public void update(final Request<Item> request, final Item entity) {

		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

	
}
