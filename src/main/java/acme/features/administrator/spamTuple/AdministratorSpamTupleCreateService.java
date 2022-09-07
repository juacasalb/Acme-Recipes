package acme.features.administrator.spamTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.system.configuration.SpamTuple;
@Service
public class AdministratorSpamTupleCreateService implements AbstractCreateService<Administrator, SpamTuple>{

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	@Override
	public boolean authorise(final Request<SpamTuple> request) {
		assert request != null;
		return true;
	}

	@Override
	public SpamTuple instantiate(final Request<SpamTuple> request) {
		assert request != null;
		SpamTuple res;
		res = new SpamTuple();
		return res;
	}

	@Override
	public void unbind(final Request<SpamTuple> request, final SpamTuple entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "spamWord", "threshold");
	}
	
	@Override
	public void bind(final Request<SpamTuple> request, final SpamTuple entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "spamWord", "threshold");
	}
	
	@Override
	public void validate(final Request<SpamTuple> request, final SpamTuple entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<SpamTuple> request, final SpamTuple entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	

}
