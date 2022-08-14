package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuantityListService implements AbstractListService<Chef, Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		
		assert request != null;
		int id;
		int chefId;
		Recipe recipe;
		final Principal principal;

		id = request.getModel().getInteger("masterId");
		recipe = this.repository.findRecipeById(id);
		principal = request.getPrincipal();
		chefId=principal.getActiveRoleId();
		
		return recipe.getChef().getId()==chefId;
		
	}
	
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request){
		
		int recipeId;
		ItemType type;
		
		recipeId = request.getModel().getInteger("masterId");
		type =ItemType.valueOf((String)request.getModel().getAttribute("type")); 
		
		return this.repository.findManyQuantitiesByRecipeIdAndType(recipeId, type);
		
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int masterId;
		Recipe recipe;
		String type;

		masterId = request.getModel().getInteger("masterId");
		recipe = this.repository.findRecipeById(masterId);
		type = request.getModel().getAttribute("type").toString();
		model.setAttribute("published", recipe.isPublished());
		model.setAttribute("type", type);
		model.setAttribute("masterId", masterId);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 

		request.unbind(entity, model, "quantity", "name", "type", "code", "unit", "retailPrice", "recipe.published"); 
		
	}
	
		

}
