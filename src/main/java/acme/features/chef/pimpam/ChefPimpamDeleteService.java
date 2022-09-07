package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefPimpamDeleteService implements AbstractDeleteService<Chef, Pimpam>{
	
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
		
		request.bind(entity, errors, "code","instantiationMoment","title","description","budget","link","periodStart","periodEnd");
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

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		final int pimpamId = request.getModel().getInteger("id");
		return this.repository.getPimpamById(pimpamId);
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors()) {
			//Comprueba que el item no este publicado
			errors.state(request, !entity.getItem().getPublished(), "instantiationMoment", "error.item-is-published");
			//Comprueba que el item pertenezca al que borra el pimpum
			errors.state(request, entity.getItem().getChef().getId()==request.getPrincipal().getActiveRoleId(), "instantiationMoment", "error.item.not-owner-of-item");
		}
		
	}

	@Override
	public void delete(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}

}
