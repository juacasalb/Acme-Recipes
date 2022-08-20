package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishDeleteService implements AbstractDeleteService<Epicure, FineDish> {

	
	@Autowired
	protected EpicureFineDishRepository repository;
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		final int roleId = request.getPrincipal().getActiveRoleId();
		final int dishId = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findFineDishByDishId(dishId);
		
		return roleId == fineDish.getEpicure().getId();
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final Model model = request.getModel();
		if(!model.hasAttribute("chef.userAccount.username")) {
			model.setAttribute("chef.userAccount.username", "chef1");
			final Chef chef = this.repository.findChefByUsername("chef1");
			model.setAttribute("chef", chef);
		} else {
			final String chefun = model.getString("chef.userAccount.username");
			final Chef chef = this.repository.findChefByUsername(chefun);
			model.setAttribute("chef", chef);
		}
		request.setModel(model);
		request.bind(entity, errors, "state","code","request","budget","startPeriod","endPeriod","moreInfo","chef");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		request.unbind(entity, model, "state","code","request","budget","startPeriod","endPeriod","moreInfo","chef.userAccount.username");
		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		
		final int dishId = request.getModel().getInteger("id");
		final FineDish result = this.repository.findFineDishByDishId(dishId);
		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert entity != null;
		assert request != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
