package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefQuantityDeleteService implements AbstractDeleteService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		
		assert request != null;

		boolean checkPublished = true;
		final int id;
		Recipe recipe;
		
		id = request.getModel().getInteger("id");
		recipe = this.repository.findQuantityById(id).getRecipe();
		checkPublished = (recipe != null && recipe.isPublished()) && request.isPrincipal(recipe.getChef());
		
		return checkPublished;
		
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

		request.unbind(entity, model, "quantity", "name", "unit", "description", "retailPrice", "link", "type");
		model.setAttribute("published", entity.getRecipe().isPublished());
		model.setAttribute("itemId", entity.getItem().getId());
	}
	
	@Override
	public Quantity findOne(final Request<Quantity> request) {
		 
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findQuantityById(id);
		
	}
	
	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
