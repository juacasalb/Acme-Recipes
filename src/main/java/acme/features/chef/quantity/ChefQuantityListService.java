package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuantityListService implements AbstractListService<Chef, Quantity>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		int id;
		int chefId;
		int userId;
		
		id=request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		chefId = recipe.getChef().getId();
		userId= request.getPrincipal().getAccountId();
		final Chef chef2 =this.repository.findOneChefByUserAccountId(userId);
		final int chefUser =chef2.getId();

		return chefId == chefUser;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		final int recipeId;
		recipeId = request.getModel().getInteger("id");
		
		return this.repository.findQuantityByRecipeId(recipeId);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null; 
		assert !CollectionHelper.someNull(entities); 
		assert model != null; 
		
		int id;
		final Recipe recipe;
		
		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		
		final boolean showCreate = (!recipe.isPublished() && request.isPrincipal(recipe.getChef()));
		
		model.setAttribute("id", id);
		model.setAttribute("showCreate", showCreate);
		 
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
 
		request.unbind(entity, model, "number", "item.type", "item.name", "item.retailPrice"); 
		 
	}
}
