package acme.features.chef.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefQuantityController extends AbstractController<Chef, Quantity>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefQuantityListService listService;
	
	@Autowired
	protected ChefQuantityShowService showService;
	
	@Autowired
	protected ChefQuantityCreateService createService;
	
	@Autowired
	protected ChefQuantityDeleteService deleteService;
	
	@Autowired
	protected ChefQuantityUpdateService updateService;
		
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
	}

}
