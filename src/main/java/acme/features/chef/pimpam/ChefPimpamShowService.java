package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
	
		Integer id;
		Pimpam item;
		id = request.getModel().getInteger("id");
		item = this.repository.findPimpamById(id);
		
		return item;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "instantationMoment", "item.name");
		
	}
	
}
