package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefQuantityUpdateService implements AbstractUpdateService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		
		assert request != null;
		boolean result;

		Quantity quantity;
		int id;

		id = request.getModel().getInteger("id");
		quantity = this.repository.findQuantityById(id);
		result = quantity.getRecipe().isPublished() && request.isPrincipal(quantity.getRecipe().getChef());
		return result;
		
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "quantity");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "quantity", "name", "unit", "code", "description", "retailPrice", "type");
		model.setAttribute("type", entity.getItem().getType().toString());
		model.setAttribute("published", entity.getRecipe().isPublished());
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {

		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(id);

		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		if(!errors.hasErrors("quantity")) {
			errors.state(request, entity.getNumber() > 0, "quantity", "chef.quantity.form.error.negative-number");
		}
					
		if(!errors.hasErrors("quantity") && entity.getItem().getType().equals(ItemType.KITCHENUTENSIL)) {
			errors.state(request, entity.getNumber() == 1, "quantity", "chef.quantity.form.error.incorrect-tool-quantity");
		}
		
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {

		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

	
}
