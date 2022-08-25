package acme.features.administrator.currencyConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.system.configuration.CurrencyConfiguration;

@Controller
public class AdministratorCurrencyConfigurationController extends AbstractController<Administrator,CurrencyConfiguration>{

	@Autowired
	protected AdministratorCurrencyConfigurationShowService showService;
	
	@Autowired
	protected AdministratorCurrencyConfigurationUpdateService updateService;

	
	@PostConstruct
	public void initialise() {
		super.addCommand("show" , this.showService);
		super.addCommand("update", this.updateService);

	}
}
