package acme.features.authenticated.chef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class AuthenticatedChefUpdateService implements AbstractUpdateService<Authenticated,Chef>{

	@Autowired
	protected AuthenticatedChefRepository repository;
	
	@Override
	public boolean authorise(final Request<Chef> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Chef> request, final Chef entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "organisation","assertion","link");
	}

	@Override
	public void unbind(final Request<Chef> request, final Chef entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "organisation","assertion","link");
	}

	@Override
	public Chef findOne(final Request<Chef> request) {
		assert request != null;
		final int accountId = request.getPrincipal().getAccountId();
		final Chef chef = this.repository.findChefByAccountId(accountId);
		return chef;
	}

	@Override
	public void validate(final Request<Chef> request, final Chef entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Chef> request, final Chef entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
