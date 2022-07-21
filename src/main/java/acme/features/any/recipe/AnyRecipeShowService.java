package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{
	
	@Autowired
	protected AnyRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		boolean result;
		int id;
		Recipe recipe;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);

		result = recipe !=null && recipe.isPublished();
		
		return result;
	}

	@Override 
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);

		return result;
	}
	
	private Money recipePrice(final int id) {
		
		final Money result = new Money();
		Money retailPrice;
		
		final String systemCurrency = this.repository.findSystemCurrency();
		Double amount = 0.0;
		result.setCurrency(systemCurrency);
		
		final Collection<Quantity> quantities = this.repository.findQuantityByRecipeId(id);
		for(final Quantity quantity:quantities) {
			retailPrice= quantity.getItem().getRetailPrice();
			final Integer itemNumber = quantity.getNumber();		
			amount = amount + retailPrice.getAmount()*itemNumber;	
		}	
	
		result.setAmount(amount);
		return result;
					
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money retailPrice;
		retailPrice = this.recipePrice(entity.getId());
		model.setAttribute("retailPrice", retailPrice);
		
		String chef;
		chef = entity.getChef().getUserAccount().getUsername();
		model.setAttribute("chef", chef);

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "published");
	}		

	
}
