package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean res = false;
		
		int quantityId;
		quantityId = request.getModel().getInteger("id");
		final Quantity quantity = this.repository.findOneQuantityById(quantityId);
		
		int recipeId;
		recipeId = quantity.getRecipe().getId();
		final Recipe recipe = this.repository.findOneRecipeById(recipeId);
		
		int userId;
		userId = request.getPrincipal().getAccountId();
		
		int chefId;
		chefId = recipe.getChef().getId();
		final Chef chef = this.repository.findOneChefByUserAccountId(userId);
		
		final int chefIdUser = chef.getId();		
		
		res = chefIdUser == chefId;
		
		return res;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("recipePublished", entity.getRecipe().isPublished());
		
		request.unbind(entity, model, "number", "recipe.code", "item.type", "item.name", "item.code", "item.retailPrice");
	}
	
	
}
