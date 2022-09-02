package acme.features.chef.pimpam;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {

		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "code", "description", "budget", "link", "item.name");
		entity.setInstantationMoment(moment);
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "instantationMoment", "item.name", "published");
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {

		assert request != null;
		
		Pimpam pimpam;
		
		pimpam = new Pimpam();
		pimpam.setPublished(false);
		
		return pimpam;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
	
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {

		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		
		this.repository.save(entity);
		
	}

	
}
