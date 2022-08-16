package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemConfigurationController extends AbstractController<Administrator,SystemConfiguration>{

	@Autowired
	protected AdministratorSystemConfigurationShowService showService;

	
	public void initialise() {
		super.addCommand("show", this.showService);

	}
}
