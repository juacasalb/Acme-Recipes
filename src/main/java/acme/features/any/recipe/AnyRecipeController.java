package acme.features.any.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Recipe;
import acme.features.any.item.AnyRecipeItemListService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyRecipeController extends AbstractController<Any, Recipe>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyRecipeListPublishedService listService;

	@Autowired
	protected AnyRecipeShowService showService;

	@Autowired
	protected AnyRecipeItemListService listItemService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
	
}
