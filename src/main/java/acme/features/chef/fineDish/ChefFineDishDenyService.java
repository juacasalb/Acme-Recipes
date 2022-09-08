package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.State;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefFineDishDenyService implements AbstractUpdateService<Chef,FineDish>{

	
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
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Model model = request.getModel();
		model.setAttribute("readOnly", true);
		request.setModel(model);
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model,"state","code","request","helping","startPeriod","endPeriod","moreInfo","epicure.identity.name","epicure.identity.surname","epicure.identity.email");		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		final int dishId = request.getModel().getInteger("id");
		final FineDish result = this.repository.findFineDishByDishId(dishId);
		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		entity.setState(State.DENIED);
		this.repository.save(entity);
	}

}
