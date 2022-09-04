package acme.features.epicure.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Epicure;

@Service
public class EpicurePimpamDeleteService implements AbstractDeleteService<Epicure, Pimpam>{

	@Autowired
	protected EpicurePimpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		boolean result;
		int pimpamId;
		Pimpam pimpam;
		Epicure epicure;
		
		pimpamId = request.getModel().getInteger("id");
		pimpam = this.repository.findPimpamById(pimpamId);
		epicure = pimpam.getItem().getEpicure();
		result = request.isPrincipal(epicure);
		
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

		request.unbind(entity, model, "title", "code", "description", "budget", "link", "instantationMoment", "item.name");
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
	public void delete(final Request<Pimpam> request, final Pimpam entity) {
		
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
