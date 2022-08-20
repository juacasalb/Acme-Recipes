package acme.features.epicure.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureFineDishController extends AbstractController<Epicure, FineDish>{

	@Autowired
	protected EpicureFineDishListService listService;
	@Autowired
	protected EpicureFineDishUpdateService updateService;
	@Autowired
	protected EpicureFineDishCreateService createService;
	@Autowired
	protected EpicureFineDishDeleteService deleteService;
	@Autowired
	protected EpicureFineDishShowService showService;
	@Autowired
	protected EpicureFineDishPublishService publishService;
	@Autowired
	protected EpicureFineDishListPublishedService listPublishedService;
	@Autowired
	protected EpicureFineDishListNotPublishedService listNotPublishedService;
	@Autowired
	protected EpicureFineDishListAcceptedService listAcceptedService;
	@Autowired
	protected EpicureFineDishListDeniedService listDeniedService;
	@Autowired
	protected EpicureFineDishListProposedService listProposedService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("update", this.updateService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
		super.addCommand("list-published", "list", this.listPublishedService);
		super.addCommand("list-not-published", "list", this.listNotPublishedService);
		super.addCommand("list-accepted", "list", this.listAcceptedService);
		super.addCommand("list-denied", "list", this.listDeniedService);
		super.addCommand("list-proposed", "list", this.listProposedService);
	}
}
