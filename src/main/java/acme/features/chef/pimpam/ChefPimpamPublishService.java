package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamPublishService implements AbstractUpdateService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		boolean result;
		int id;
		Pimpam pimpam;
		Chef chef;
		
		id = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(id);
		chef = pimpam.getItem().getChef();
		result = !pimpam.getPublished() && request.isPrincipal(chef);
		
		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "code", "description", "budget", "link", "instantationMoment", "item.name");
		
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "instantationMoment", "item.name", "published");
		
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {

		assert request != null;
		
		Pimpam result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findPimpamById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Pimpam> request, final Pimpam entity) {

		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}
