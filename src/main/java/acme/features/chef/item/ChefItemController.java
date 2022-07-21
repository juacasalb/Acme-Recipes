package acme.features.chef.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
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
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-my-kitchenutensils", "list", this.listMyKitchenUtensilsService);
		super.addCommand("list-my-ingredients", "list", this.listMyIngredientsService);
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
	}
	
}
