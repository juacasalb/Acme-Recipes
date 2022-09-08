package acme.features.chef.quittel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quittel;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuittelShowService implements AbstractShowService<Chef, Quittel>{

	@Autowired
	protected ChefQuittelRepository repository;
	
	@Override
	public boolean authorise(final Request<Quittel> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Quittel findOne(final Request<Quittel> request) {
		assert request != null;
	
		Integer id;
		Quittel item;
		id = request.getModel().getInteger("id");
		item = this.repository.findQuittelById(id);
		
		return item;
	}

	@Override
	public void unbind(final Request<Quittel> request, final Quittel entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "helping", "link", "instantationMoment", "finishingDate", "item.name");
		
	}
	
}
