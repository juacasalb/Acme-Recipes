package acme.features.epicure.pimpam;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicurePimpamCreateService implements AbstractCreateService<Epicure, Pimpam>{
	
	@Autowired
	protected EpicurePimpamRepository repository;
	
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
		
		final String itemCode = request.getModel().getString("itemCode");
		final Item it = this.repository.getItemFromCode(itemCode);
		entity.setItem(it);
		request.bind(entity, errors, "instantiationMoment","code","title","description","budget","link","periodStart","periodEnd");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemCode", "");
		
		request.unbind(entity, model, "instantiationMoment","code","title","description","budget","link","periodStart","periodEnd");
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;
		Pimpam result;
		result = new Pimpam();
		
		
		
		final Date instantiationMoment = Date.from(Instant.now());
		
		result.setInstantiationMoment(instantiationMoment);
		
		final String[] componentesCode = this.getDateComponents(instantiationMoment);
		
		String dd, mm, yy;
		dd = componentesCode[0];
		mm = componentesCode[1];
		yy = componentesCode[2];
		final String separador = "-";
		
		final String code = yy + separador + mm + separador + dd;
		
		result.setCode(code);
		
		return result;
	}
	
	private String[] getDateComponents(final Date input) {
		String[] result;
		result = new String[3];
		
		final LocalDate inputLocal = new java.sql.Date(input.getTime()).toLocalDate();
		int year, month, day;
		String yy, mm, dd;
		
		year = inputLocal.getYear();
		yy = Integer.toString(year);
		if(year<10) {
			result[2] = "0" + Integer.toString(year);
		}
		else if (year>99) {
			result[2] = yy.substring(yy.length()-2);
		}
		else {
			result[2] = Integer.toString(year);
		}
		
		month = inputLocal.getMonthValue();
		mm = Integer.toString(month);
		if(month<10) {
			result[1] = "0" + mm;
		}
		else {
			result[1] = mm;
		}
		
		day = inputLocal.getDayOfMonth();
		dd = Integer.toString(day);
		if(day<10) {
			result[0] = "0" + dd;
		}
		else {
			result[0] = dd;
		}
		return result;
	}
	

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors()) {
			Money budget;
			Item selectedItem;
			String itemCode, currencies;
			Date periodStart, periodEnd, instantiationMoment, earliestStart, earliestEnd;
			
			
			itemCode = request.getModel().getString("itemCode");
			selectedItem = this.repository.getItemFromCode(itemCode);
			
			budget = entity.getBudget();
			currencies = this.repository.getCurrencyConfiguration().getAcceptedCurrencies();
			
			
			
			periodStart = entity.getPeriodStart();
			periodEnd = entity.getPeriodEnd();
			instantiationMoment = entity.getInstantiationMoment();
			earliestEnd = DateUtils.addWeeks(periodStart, 1);
			earliestStart = DateUtils.addMonths(instantiationMoment, 1);
			
	//Item
			//Comprueba que el Item seleccionado exista
			errors.state(request, selectedItem!=null, "itemCode", "error.item-code.no-item-with-code");
			if(selectedItem!=null) {
				//Comprueba que el Item Seleccionado pertenezca al que solicita crear el pimpam
				errors.state(request, selectedItem.getEpicure().getId()==request.getPrincipal().getActiveRoleId(), "itemCode", "error.item.not-owner-of-item");
				//Comprueba que el Item seleccionado no este publicado
				errors.state(request, !selectedItem.getPublished(), "itemCode", "error.item.item-is-published");	
			}
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
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
				
		this.repository.save(entity);
	}
}
