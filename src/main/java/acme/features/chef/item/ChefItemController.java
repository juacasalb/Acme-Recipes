package acme.features.chef.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.features.chef.recipe.ChefRecipeItemListService;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefItemController extends AbstractController<Chef, Item>{
	
	@Autowired
	protected ChefItemListMyIngredientsService listMyIngredientsService;
	
	@Autowired
	protected ChefItemListMyKitchenUtensilsService listMyKitchenUtensilsService;
	
	@Autowired
	protected ChefItemListService listService;
	
	@Autowired
	protected ChefItemShowService showService;
	
	@Autowired
	protected ChefItemCreateService createService;
	
	@Autowired
	protected ChefItemUpdateService updateService;
	
	@Autowired
	protected ChefItemPublishService publishService;
	
	@Autowired
	protected ChefItemDeleteService deleteService;
	
	@Autowired
	protected ChefRecipeItemListService listRecipeService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-my-kitchenutensils", "list", this.listMyKitchenUtensilsService);
		super.addCommand("list-my-ingredients", "list", this.listMyIngredientsService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("show", "show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("list-item", "list", this.listRecipeService);
		super.addCommand("publish","update",this.publishService);
	}
	
}
