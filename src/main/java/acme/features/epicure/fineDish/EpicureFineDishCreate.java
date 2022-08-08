package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishCreate implements AbstractCreateService<Epicure,FineDish>{

	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "state","code","request","budget","startPeriod","endPeriod","moreInfo","chef");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		request.unbind(entity, model, "state","code","request","budget","startPeriod","endPeriod","moreInfo");
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final Epicure epicure = this.repository.findById(epicureId);
		final FineDish newDish = new FineDish();
		newDish.setEpicure(epicure);
		final Date current = new Date(System.currentTimeMillis()-1);
		final Date oneMonth = DateUtils.addMonths(current, 1);
		newDish.setStartPeriod(current);
		newDish.setEndPeriod(oneMonth);
		return newDish;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		 this.repository.save(entity);
		
	}

}
