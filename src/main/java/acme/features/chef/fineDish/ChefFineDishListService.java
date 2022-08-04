package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefFineDishListService implements AbstractListService<Chef,FineDish> {

	@Autowired
	protected ChefFineDishRepository repository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

}
