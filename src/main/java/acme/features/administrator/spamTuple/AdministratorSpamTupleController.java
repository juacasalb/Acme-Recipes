package acme.features.administrator.spamTuple;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.system.configuration.SpamTuple;

@Controller
public class AdministratorSpamTupleController extends AbstractController<Administrator,SpamTuple>{

	
	
	@Autowired
	protected AdministratorSpamTupleCreateService createService;
	@Autowired
	protected AdministratorSpamTupleDeleteService deleteService;

	
	@PostConstruct
	public void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);

	}
}
