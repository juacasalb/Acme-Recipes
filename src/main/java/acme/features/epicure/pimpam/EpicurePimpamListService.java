package acme.features.epicure.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicurePimpamListService implements AbstractListService<Epicure, Pimpam>{

	@Autowired
	protected EpicurePimpamRepository repository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		
		assert request != null;
		
		return true;
		
	}
	
	@Override
	public Collection<Pimpam> findMany(final Request<Pimpam> request){
		
		assert request != null;
		
		final int getId = request.getPrincipal().getActiveRoleId();
		Collection<Pimpam> result;
		result = this.repository.findPimpamsByEpicureId(getId);
		
		return result;
		
	}
	
	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "budget", "instantationMoment");
		
	}
	
		

}
