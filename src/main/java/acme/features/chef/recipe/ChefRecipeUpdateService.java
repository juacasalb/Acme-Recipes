package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef, Recipe>{

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
		
		if(!errors.hasErrors("code")) {
			Recipe createdRecipe;
			
			createdRecipe = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, createdRecipe == null, "code", "chef.recipe.form.error.code-duplicated");
		}
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
