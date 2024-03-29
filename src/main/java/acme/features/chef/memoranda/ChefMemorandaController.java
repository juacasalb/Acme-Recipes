package acme.features.chef.memoranda;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefMemorandaController extends AbstractController<Chef, Memorandum>{
	
	@Autowired
	protected ChefMemorandaCreateService createService;
	
	@Autowired
	protected ChefMemorandaListService listService;
	
	@Autowired
	protected ChefMemorandaShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}
