package acme.features.chef.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefRecipeController extends AbstractController<Chef, Recipe>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeListMineService listMineService;
	
	@Autowired
	protected ChefRecipeShowService showMineService;
	
	@Autowired
	protected ChefRecipeCreateService createService;
	
	@Autowired 
	protected ChefRecipeDeleteService deleteService;
	
	@Autowired 
	protected ChefRecipeUpdateService updateService;
	
	@Autowired
	protected ChefRecipePublishService publishService;
		
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listMineService);	
		super.addCommand("show", this.showMineService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
	}

}
