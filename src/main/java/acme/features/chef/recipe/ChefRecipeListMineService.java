package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefRecipeListMineService implements AbstractListService<Chef, Recipe>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;
	
	// AbstractListService<Any, Recipe> ---------------------------
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		final Principal principal = request.getPrincipal();
		return this.repository.findManyRecipesByChefId(principal.getActiveRoleId());
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "heading", "description", "published");
		
	}
	
	

}
