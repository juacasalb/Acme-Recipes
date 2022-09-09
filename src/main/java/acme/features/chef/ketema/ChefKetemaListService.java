package acme.features.chef.ketema;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ketema;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefKetemaListService implements AbstractListService<Chef, Ketema>{

	@Autowired
	protected ChefKetemaRepository repository;
	
	@Override
	public boolean authorise(final Request<Ketema> request) {
		
		assert request != null;
		
		return true;
		
	}
	
	@Override
	public Collection<Ketema> findMany(final Request<Ketema> request){
		
		assert request != null;
		
		final int getId = request.getPrincipal().getActiveRoleId();
		Collection<Ketema> result;
		result = this.repository.findKetemasByChefId(getId);
		
		return result;
		
	}
	
	@Override
	public void unbind(final Request<Ketema> request, final Ketema entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "theme", "allotment", "instantationMoment");
		
	}
	
		

}
