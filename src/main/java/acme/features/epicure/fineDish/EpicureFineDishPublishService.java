package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishPublishService implements AbstractUpdateService<Epicure, FineDish>{

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
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		request.unbind(entity, model, "code", "request", "helping","startPeriod","endPeriod", "moreInfo", "chef", "chef.userAccount.username");
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final FineDish dish = this.repository.findFineDishByDishId(id);
		return dish;
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
		entity.setPublished(true);
		this.repository.save(entity);
	}

}
