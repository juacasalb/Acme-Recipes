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
		request.bind(entity, errors,"code","request","helping","startPeriod","endPeriod","moreInfo","chef");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		request.unbind(entity, model, "state","code","request","helping","startPeriod","endPeriod","moreInfo","chef.userAccount.username");
		
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		final int epicureId = request.getPrincipal().getActiveRoleId();
		final Epicure epicure = this.repository.findById(epicureId);
		final FineDish newDish = new FineDish();
		newDish.setEpicure(epicure);
		final Date current = new Date(System.currentTimeMillis()-60);
		Date startP = DateUtils.addMonths(new Date(System.currentTimeMillis()), 1);
		startP = DateUtils.addMinutes(startP, 5);
		Date endP = DateUtils.addMonths(new Date(System.currentTimeMillis()+60), 2);
		endP = DateUtils.addMinutes(endP, 5);
		newDish.setCreationMomment(current);
		newDish.setStartPeriod(startP);
		newDish.setEndPeriod(endP);
		newDish.setState(State.PROPOSED);
		return newDish;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
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
			errors.state(request, dish == null, "code", "error.code-exists");
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
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
		
	}

}
