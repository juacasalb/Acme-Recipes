package acme.features.chef.pimpam;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
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
		request.bind(entity, errors, "title", "code", "description", "budget", "link");
		entity.setInstantationMoment(moment);
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int chefId = this.repository.findChefById(request.getPrincipal().getActiveRoleId()).getId();
		final Collection<Item> items =  this.repository.findManyAvailableItemsByChef(chefId);
		
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "instantationMoment");
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {

		assert request != null;
		
		Pimpam pimpam;
		
		pimpam = new Pimpam();
		
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
		
		this.repository.save(entity);
		
	}

	
}
