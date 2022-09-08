package acme.features.chef.quittel;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Quittel;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefQuittelController extends AbstractController<Chef, Quittel>{
	
	@Autowired
	protected ChefQuittelListService listService;
	
	@Autowired
	protected ChefQuittelShowService showService;
	
	@Autowired
	protected ChefQuittelCreateService createService;
	
	@Autowired
	protected ChefQuittelUpdateService updateService;
	
	@Autowired
	protected ChefQuittelDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", "show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
	
}
