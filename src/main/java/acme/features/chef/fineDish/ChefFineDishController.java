package acme.features.chef.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefFineDishController extends AbstractController<Chef,FineDish> {

	@Autowired
	protected ChefFineDishListService listService;
	@Autowired
	protected ChefFineDishShowService showService;
	@Autowired
	protected ChefFineDishAcceptService acceptService;
	@Autowired
	protected ChefFineDishDenyService denyService;
	@Autowired
	protected ChefFineDishListProposedService listProposedService;
	@Autowired
	protected ChefFineDishListAcceptedService listAcceptedService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("accept", "update", this.acceptService);
		super.addCommand("deny", "update", this.denyService);
		super.addCommand("list-proposed","list", this.listProposedService);
		super.addCommand("list-accepted","list" , this.listAcceptedService);
	}
}
