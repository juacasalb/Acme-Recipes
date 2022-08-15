package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure,FineDish> {
	
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
		request.bind(request,errors,"request","budget","startPeriod","endPeriod","moreInfo","chef");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"state","code","request","budget","startPeriod","endPeriod","moreInfo","chef.userAccount.username");
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
		if(!errors.hasErrors("budget")) {
			final Money budget = entity.getBudget();
			final String currencies = this.repository.acceptedCurrencies();
			final String[] currenciesArr = currencies.split(",");
			final String budgetCurrency = budget.getCurrency();
			final double amount = budget.getAmount();
			errors.state(request, amount>0 ,"budget", "error.budget-amount");
			boolean isRealCurrency = false;
			for(final String curr:currenciesArr) {
				if(curr.equals(budgetCurrency)) {
					isRealCurrency = true;
					break;
				}
			}
			errors.state(request, isRealCurrency ,"budget", "error.budget-currency");
		}
		if(!errors.hasErrors("code")) {
			final FineDish dish = this.repository.findDishByCode(entity.getCode()); 
			errors.state(request, dish == null||dish.getCode().equals(entity.getCode()), "code", "error.code-exists");
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
		this.repository.save(entity);
		
	}

	
}
