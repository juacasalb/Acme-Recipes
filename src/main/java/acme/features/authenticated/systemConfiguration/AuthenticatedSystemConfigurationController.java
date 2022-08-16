package acme.features.authenticated.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedSystemConfigurationController extends AbstractController<Authenticated,SystemConfiguration> {

	@Autowired
	protected AuthenticatedSystemConfigurationShowService showService;
	
	@PostConstruct
	public void initialise() {
		super.addCommand("show-config","show", this.showService);
	}
}
