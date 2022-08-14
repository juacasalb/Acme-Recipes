package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateService implements AbstractCreateService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean checkPublished = true;
		int masterId;
		Recipe recipe;

		masterId = request.getModel().getInteger("masterId");
		recipe = this.repository.findRecipeById(masterId);
		checkPublished = (recipe != null && recipe.isPublished()) && request.isPrincipal(recipe.getChef());
		
		return checkPublished;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.repository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
		request.bind(entity, errors, "quantity", "itemId");
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int masterId = request.getModel().getInteger("masterId");
		final ItemType type = ItemType.valueOf((String)request.getModel().getAttribute("type"));
		
		final Recipe t = this.repository.findRecipeById(Integer.valueOf(request.getModel().getAttribute("masterId").toString()));
		model.setAttribute("published", t.isPublished());
		model.setAttribute("type",  type.toString());
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("items", this.repository.findManyItemsNotInRecipe(masterId,type));
		request.unbind(entity, model, "quantity");
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {

		assert request != null;
		
		final Quantity q = new Quantity();
		final int toolkitId = request.getModel().getInteger("masterId");
		q.setRecipe(this.repository.findRecipeById(toolkitId));
		q.setNumber(1);
		
		return q;
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
			errors.state(request, entity.getNumber() == 1, "quantity", "chef.quantity.form.error.incorrect-kitchenutensil-quantity");
		}
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {

		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

	
}
