package acme.features.authenticated.epicure;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Epicure;

@Controller
public class AuthenticatedEpicureController extends AbstractController<Authenticated, Epicure>{
	
	@Autowired
	protected AuthenticatedEpicureCreateService createService;
	@Autowired
	protected AuthenticatedEpicureUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}
}
