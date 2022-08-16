package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.features.chef.item.ChefItemRepository;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateService implements AbstractCreateService<Chef, Quantity>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected ChefItemRepository itemRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int id;
		Recipe recipe;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		result = (recipe != null && !recipe.isPublished() && request.isPrincipal(recipe.getChef()));
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setItem(this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
		final int id = this.repository.findOneRecipeById(request.getModel().getInteger("id")).getId();
		entity.setId(id);
		
		request.bind(entity, errors, "number", "itemId");		
	}	

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 

		final int masterId = Integer.parseInt((String) request.getModel().getAttribute("id"));
		final Collection<Item> items =  this.itemRepository.findManyPublishedAndValidItems(masterId);
		
		model.setAttribute("masterId", masterId);
		model.setAttribute("items", items);
		request.unbind(entity, model, "number");
	}
	


	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		result= new Quantity();
		
		final Recipe recipe = this.repository.findOneRecipeById(request.getModel().getInteger("id"));
		
		result.setRecipe(recipe);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Item item = entity.getItem();
		
		if(item.getType().equals(ItemType.INGREDIENT)) {
			errors.state(request, entity.getNumber() == 1, "number", "inventor.quantity.form.error.recipe-one-ingredient");
		}
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;	
		
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Quantity> request, final Response<Quantity> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
