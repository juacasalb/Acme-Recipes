package acme.features.chef.ketema;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Ketema;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefKetemaController extends AbstractController<Chef, Ketema>{
	
	@Autowired
	protected ChefKetemaListService listService;
	
	@Autowired
	protected ChefKetemaShowService showService;
	
	@Autowired
	protected ChefKetemaCreateService createService;
	
	@Autowired
	protected ChefKetemaUpdateService updateService;
	
	@Autowired
	protected ChefKetemaDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", "show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
	
}
