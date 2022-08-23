package acme.features.chef.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipePublishService implements AbstractUpdateService<Chef, Recipe>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		boolean result;
		int recipeId;
		Recipe recipe;
		Chef chef;
		
		recipeId = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(recipeId);
		chef = recipe.getChef();
		
		result = !recipe.isPublished() && request.isPrincipal(chef);
		
		return result;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "heading", "description", "preparationNotes",
			"link", "chef.userAccount.username", "retailPrice");
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int masterId;
		masterId = request.getModel().getInteger("id");
		
		model.setAttribute("id", masterId);

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes",
			"link", "chef.userAccount.username", "retailPrice", "published");	
		
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe recipe;
		int id;
		
		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		return recipe;
	}
	
	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		final Collection<Quantity> quantity = this.repository.findQuantityByRecipeId(recipeId);
		final Collection<Item> items = new HashSet<Item>();
		Boolean publish = true;
		
		for(final Quantity q:quantity) {
			final int id = q.getId();
			final Collection<Item> iq = this.repository.findManyItemsByQuantityId(id);
			items.addAll(iq);
		}
		
		errors.state(request, !items.isEmpty(), "*", "chef.recipe.form.error.items-empty");

		for(final Item i:items) {
			publish = publish && i.getPublished();
		}
		
		errors.state(request, publish, "*", "chef.recipe.form.error.items-no-published");
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
	}
	
	

}
