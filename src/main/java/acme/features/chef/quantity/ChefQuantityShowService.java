package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
assert request != null;
		
		final boolean result;
		int id;
		int inventorId;
		Recipe recipe;
		final Principal principal;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findQuantityById(id).getRecipe();
		principal = request.getPrincipal();
		inventorId=principal.getActiveRoleId();
		


		result = recipe.getChef().getId()==inventorId;
		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		Item item;
		int masterId;

		masterId = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(masterId);
		
		item = result.getItem();
		
		this.repository.save(item);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 

		request.unbind(entity, model, "quantity", "name", "type", "code", "description", "unit", "retailPrice"); 
		model.setAttribute("type", entity.getItem().getType().toString());
		model.setAttribute("published", entity.getRecipe().isPublished());
		
	}
	
}
