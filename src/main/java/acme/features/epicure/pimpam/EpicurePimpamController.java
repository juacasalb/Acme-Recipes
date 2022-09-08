//package acme.features.epicure.quittel;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import acme.entities.Quittel;
//import acme.framework.controllers.AbstractController;
//import acme.roles.Epicure;
//
//@Controller
//public class EpicureQuittelController extends AbstractController<Epicure, Quittel>{
//	
//	@Autowired
//	protected EpicureQuittelListService listService;
//	
//	@Autowired
//	protected EpicureQuittelShowService showService;
//	
////	@Autowired
////	protected EpicureQuittelCreateService createService;
//	
//	@Autowired
//	protected EpicureQuittelUpdateService updateService;
//	
//	@Autowired
//	protected EpicureQuittelDeleteService deleteService;
//
//	@PostConstruct
//	protected void initialise() {
//		super.addCommand("list", this.listService);
//		super.addCommand("show", "show", this.showService);
//		//super.addCommand("create", this.createService);
//		super.addCommand("update", this.updateService);
//		super.addCommand("delete", this.deleteService);
//	}
//	
//}
