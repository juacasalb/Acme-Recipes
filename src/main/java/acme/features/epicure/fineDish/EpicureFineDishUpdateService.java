package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.State;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure,FineDish>{

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
		final Model model = request.getModel();
		if(model.hasAttribute("chef.userAccount.username")) {
			final String usrname = model.getString("chef.userAccount.username");
			final Chef chef = this.repository.findChefByUsername(usrname);
			model.setAttribute("chef", chef);
		} else {
			final Chef chef = this.repository.findChefByUsername("chef1");
			model.setAttribute("chef", chef);
			model.setAttribute("chef.userAccount.username", "chef1");
		}
		request.setModel(model);
		request.bind(entity, errors,"code", "request", "helping","startPeriod","endPeriod", "moreInfo", "chef");
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model,"state", "code", "request", "helping","startPeriod","endPeriod", "moreInfo", "chef", "chef.userAccount.username","published");
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
		if(!errors.hasErrors("helping")) {
			final Money helping = entity.getHelping();
			final String currencies = this.repository.findCurrencyConiguration().getAcceptedCurrencies();
			final String helpingCurrency = helping.getCurrency();
			final double amount = helping.getAmount();
			errors.state(request, amount>0 ,"helping", "error.helping-amount");
			boolean isRealCurrency = false;
			if(currencies.contains(helpingCurrency)) {
				isRealCurrency = true;
			}
			errors.state(request, isRealCurrency ,"helping", "error.helping-currency");
		}
		if(!errors.hasErrors("code")) {
			final FineDish dish = this.repository.findDishByCode(entity.getCode()); 
			errors.state(request, dish == null||dish.equals(entity), "code", "error.code-exists");
 		}
		if(!errors.hasErrors("startPeriod")) {
			final Date creation = entity.getCreationMomment();
			final Date soonAfter = DateUtils.addMinutes(DateUtils.addMonths(creation, 1), -1);
			errors.state(request, entity.getStartPeriod().after(soonAfter), "startPeriod", "error.startPeriodTooSoon");
		}
		if(!errors.hasErrors("endPeriod")) {
			final Date startP = entity.getStartPeriod();
			final Date soonAfter = DateUtils.addMinutes(DateUtils.addMonths(startP, 1), -1);
			errors.state(request,entity.getEndPeriod().after(soonAfter), "endPeriod", "error.endPeriodTooSoon");
		}
		if(!errors.hasErrors("chef.userAccount.username")) {
			final Chef chef = this.repository.findChefByUsername(request.getModel().getString("chef.userAccount.username"));
			errors.state(request, chef!=null, "chef.userAccount.username", "error.chef-username-not-found");
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(false);
		entity.setState(State.PROPOSED);
		this.repository.save(entity);
	}

}
