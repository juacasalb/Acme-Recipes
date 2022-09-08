package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish>{
	
	@Autowired
	protected ChefFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
assert request != null;
		
		final int roleId = request.getPrincipal().getActiveRoleId();
		final int dishId = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findFineDishByDishId(dishId);
		
		return roleId == fineDish.getChef().getId();
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		final int dishId = request.getModel().getInteger("id");
		final FineDish result = this.repository.findFineDishByDishId(dishId);
		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model,"state","code","request","helping","startPeriod","endPeriod","moreInfo","epicure.identity.name","epicure.identity.surname","epicure.identity.email");
		model.setAttribute("readOnly", true);
		
	}

}
