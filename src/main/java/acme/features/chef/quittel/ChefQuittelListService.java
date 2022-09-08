package acme.features.chef.quittel;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Quittel;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuittelListService implements AbstractListService<Chef, Quittel>{

	@Autowired
	protected ChefQuittelRepository repository;
	
	@Override
	public boolean authorise(final Request<Quittel> request) {
		
		assert request != null;
		
		return true;
		
	}
	
	@Override
	public Collection<Quittel> findMany(final Request<Quittel> request){
		
		assert request != null;
		
		final int getId = request.getPrincipal().getActiveRoleId();
		Collection<Quittel> result;
		result = this.repository.findQuittelsByChefId(getId);
		
		return result;
		
	}
	
	@Override
	public void unbind(final Request<Quittel> request, final Quittel entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "helping", "instantationMoment");
		
	}
	
		

}
