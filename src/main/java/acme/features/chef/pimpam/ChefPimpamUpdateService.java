package acme.features.chef.pimpam;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamUpdateService implements AbstractUpdateService<Chef, Pimpam>{
	
	
	@Autowired
	protected ChefPimpamRepository repository;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "instantiationMoment","code","title","description","budget","link","periodStart","periodEnd");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("published", entity.getItem().getPublished());
		model.setAttribute("itemCode", entity.getItem().getCode());
		
		request.unbind(entity, model, "code","instantiationMoment","title","description","budget","link","periodStart","periodEnd");
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		final int pimpamId = request.getModel().getInteger("id");
		return this.repository.getPimpamById(pimpamId);
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors()) {
			Money budget;
			String currencies;
			Date periodStart, periodEnd, instantiationMoment, earliestEnd, earliestStart;
			
	//Budget
			budget = entity.getBudget();
			currencies = this.repository.getCurrencyConfiguration().getAcceptedCurrencies();
			
	//Periodo
			periodStart = entity.getPeriodStart();
			periodEnd = entity.getPeriodEnd();
			instantiationMoment = entity.getInstantiationMoment();
			
			earliestEnd = DateUtils.addWeeks(periodStart, 1);
			earliestStart = DateUtils.addMonths(instantiationMoment, 1);
			
	//Item
			//Comprueba que el item no este publicado
			errors.state(request, !entity.getItem().getPublished(), "instantiationMoment", "error.item.item-is-published");
			//Comprueba que el item pertenezca al que hace el update
			errors.state(request, entity.getItem().getChef().getId()==request.getPrincipal().getActiveRoleId(), "instantiationMoment", "error.item.not-owner-of-item");
	//Budget
			//Comprueba que no sea una cantidad negativa
			errors.state(request, budget.getAmount()>0, "budget", "error.budget-negative-amount");
			//Comprueba que sea una moneda valida
			errors.state(request, currencies.contains(budget.getCurrency()), "budget", "error.budget-invalid-currency");
	//Periodo
			//Comprueba que el periodo dure al menos 1 semana
			errors.state(request, !periodEnd.before(earliestEnd), "periodEnd", "error.period-too-short");
			//Comprueba que el periodo empiece al menos un mes despues de instantiationMoment
			errors.state(request, !periodStart.before(earliestStart), "periodStart", "error.period-too-early");
		}
	}

	@Override
	public void update(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
