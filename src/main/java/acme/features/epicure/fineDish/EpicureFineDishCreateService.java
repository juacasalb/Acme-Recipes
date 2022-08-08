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
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure,FineDish>{

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
		assert request.getModel().hasAttribute("chef-id");
		final String chefId = request.getModel().getString("chef-id");
		Chef chefFromUN;
		if(chefId!=null) {
			chefFromUN = this.repository.findChefByUsername(chefId);
		}
		request.bind(entity, errors, "state","code","request","budget","startPeriod","endPeriod","moreInfo","chef");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		request.unbind(entity, model, "state","code","request","budget","startPeriod","endPeriod","moreInfo");
		model.setAttribute("chef-id", "chef1");
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
