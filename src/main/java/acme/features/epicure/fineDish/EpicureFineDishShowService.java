package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishShowService implements AbstractShowService<Epicure, FineDish>{

	@Autowired
	protected EpicureFineDishRepository repository;
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int dishId = request.getModel().getInteger("id");
		final int ownerId = this.repository.findFineDishByDishId(dishId).getEpicure().getId();
		return epicureId==ownerId;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final FineDish dish = this.repository.findFineDishByDishId(id);
		return dish;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "state", "code", "request","budget","startPeriod","endPeriod","moreInfo", "published", "chef", "chef.userAccount.username");
	}

}
