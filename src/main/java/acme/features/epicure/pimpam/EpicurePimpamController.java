package acme.features.epicure.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicurePimpamController extends AbstractController<Epicure, Pimpam>{
	
	@Autowired
	protected EpicurePimpamListService listService;
	
	@Autowired
	protected EpicurePimpamShowService showService;
	
//	@Autowired
//	protected EpicurePimpamCreateService createService;
	
	@Autowired
	protected EpicurePimpamUpdateService updateService;
	
	@Autowired
	protected EpicurePimpamDeleteService deleteService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", "show", this.showService);
		//super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
	
}
