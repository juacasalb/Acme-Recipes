package acme.features.administrator.spamTuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractDeleteService;
import acme.system.configuration.SpamTuple;

@Service
public class AdministratorSpamTupleDeleteService implements AbstractDeleteService<Administrator, SpamTuple>{

	
	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamTuple> request) {
		assert request != null;
		return true;
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
	public SpamTuple findOne(final Request<SpamTuple> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final SpamTuple result = this.repository.findSpamTupleById(id);
		return result;
	}
	
	
	@Override
	public void validate(final Request<SpamTuple> request, final SpamTuple entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<SpamTuple> request, final SpamTuple entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
	
}
