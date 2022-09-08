package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.State;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefFineDishListAcceptedService implements AbstractListService<Chef,FineDish>{

	@Autowired
	protected ChefFineDishRepository repository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		assert request != null;
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Collection<FineDish> dishes = this.repository.findDishesByChefIdAndState(chefId, State.ACCEPTED);
		return dishes;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "helping","startPeriod", "endPeriod");
		
	}

}
