package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
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
		final int pimpamId = request.getModel().getInteger("id");
		final Item it = this.repository.getPimpamById(pimpamId).getItem();
		return it.getChef().getId()==request.getPrincipal().getActiveRoleId();
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		final Integer id = request.getModel().getInteger("id");
		return this.repository.getPimpamById(id);
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("published", entity.getItem().getPublished());
		model.setAttribute("itemCode", entity.getItem().getCode());
		
		request.unbind(entity, model, "code","instantiationMoment","title","description","budget","link","periodStart","periodEnd");
		
	}

}
