package acme.features.authenticated.systemConfiguration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;
import acme.system.configuration.CurrencyConfiguration;
import acme.system.configuration.SpamTuple;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated,SystemConfiguration>{

	@Autowired 
	protected AuthenticatedSystemConfigurationRepository repository;
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		final SystemConfiguration sysCon = new SystemConfiguration();
		final CurrencyConfiguration currCon = this.repository.findCurrencyConfiguration();
		final List<SpamTuple> spamTuples = this.repository.findAllSpamTuple();
		sysCon.setCurrencyConfiguration(currCon);
		sysCon.setSpamConfiguration(spamTuples);
		return sysCon;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "currencyConfiguration");
		
	}

}
