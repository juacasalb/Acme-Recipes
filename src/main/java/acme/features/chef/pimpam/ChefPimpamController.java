package acme.features.chef.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPimpamController extends AbstractController<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamListService listService;
	
	@Autowired
	protected ChefPimpamShowService showService;
	
	@Autowired
	protected ChefPimpamCreateService createService;
	
	@Autowired
	protected ChefPimpamUpdateService updateService;
	
	@Autowired
	protected ChefPimpamDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", "show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
	
}
