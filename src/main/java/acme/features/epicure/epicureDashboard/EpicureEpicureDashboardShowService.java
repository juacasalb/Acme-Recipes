package acme.features.epicure.epicureDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.State;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureEpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard>{
	
	@Autowired
	protected EpicureEpicureDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;
		
		State s;
		Integer count;
		
		Map<String, Double> acceptedMap, deniedMap, proposedMap;
		acceptedMap = new HashMap<>();
		proposedMap = new HashMap<>();
		deniedMap = new HashMap<>();
		
		EpicureDashboard result;
		List<Object[]> fineDishesList;
		
		
		
		final Integer epicureId = request.getPrincipal().getActiveRoleId();
		
		
		fineDishesList = this.repository.countFineDishesOfState(epicureId);
		result = new EpicureDashboard();
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			count = (Integer) o[1];
			switch(s) {
			case PROPOSED:
				result.setTotalNProposedFineDishes(count);
				break;
			case DENIED:
				result.setTotalNDeniedFineDishes(count);
				break;
			case ACCEPTED:
				result.setTotalNAcceptedFineDishes(count);
				break;
			}
		}
		String currency;
		Double value;
		
		
		fineDishesList = this.repository.averageBudgetFineDishesOfStateByCurrency(epicureId);
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			currency = (String) o[1];
			value = (Double) o[2];
			switch(s) {
			case PROPOSED:
				proposedMap.put(currency, value);
				break;
			case ACCEPTED:
				acceptedMap.put(currency, value);
				break;
			case DENIED:
				deniedMap.put(currency, value);
				break;
			}
		}
		result.setAverageBudgetAcceptedFineDishesByCurrency(acceptedMap);
		result.setAverageBudgetDeniedFineDishesByCurrency(deniedMap);
		result.setAverageBudgetProposedFineDishesByCurrency(proposedMap);
		
		acceptedMap.clear();
		deniedMap.clear();
		proposedMap.clear();
		
		fineDishesList = this.repository.deviationBudgetFineDishesOfStateByCurrency(epicureId);
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			currency = (String) o[1];
			value = (Double) o[2];
			switch(s) {
			case PROPOSED:
				proposedMap.put(currency, value);
				break;
			case ACCEPTED:
				acceptedMap.put(currency, value);
				break;
			case DENIED:
				deniedMap.put(currency, value);
				break;
			}
		}
		result.setDeviationBudgetAcceptedFineDishesByCurrency(acceptedMap);
		result.setDeviationBudgetDeniedFineDishesByCurrency(deniedMap);
		result.setDeviationBudgetProposedFineDishesByCurrency(proposedMap);
		
		acceptedMap.clear();
		deniedMap.clear();
		proposedMap.clear();
		
		fineDishesList = this.repository.minBudgetFineDishesOfStateByCurrency(epicureId);
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			currency = (String) o[1];
			value = (Double) o[2];
			switch(s) {
			case PROPOSED:
				proposedMap.put(currency, value);
				break;
			case ACCEPTED:
				acceptedMap.put(currency, value);
				break;
			case DENIED:
				deniedMap.put(currency, value);
				break;
			}
		}
		result.setMinBudgetAcceptedFineDishesByCurrency(acceptedMap);
		result.setMinBudgetDeniedFineDishesByCurrency(deniedMap);
		result.setMinBudgetProposedFineDishesByCurrency(proposedMap);
		
		acceptedMap.clear();
		deniedMap.clear();
		proposedMap.clear();
		
		fineDishesList = this.repository.maxBudgetFineDishesOfStateByCurrency(epicureId);
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			currency = (String) o[1];
			value = (Double) o[2];
			switch(s) {
			case PROPOSED:
				proposedMap.put(currency, value);
				break;
			case ACCEPTED:
				acceptedMap.put(currency, value);
				break;
			case DENIED:
				deniedMap.put(currency, value);
				break;
			}
		}
		result.setMaxBudgetAcceptedFineDishesByCurrency(acceptedMap);
		result.setMaxBudgetDeniedFineDishesByCurrency(deniedMap);
		result.setMaxBudgetProposedFineDishesByCurrency(proposedMap);
		
		acceptedMap.clear();
		deniedMap.clear();
		proposedMap.clear();
		
		return result;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
				
		request.unbind(entity, model, "totalNProposedFineDishes", "totalNAcceptedFineDishes", "totalNDeniedFineDishes",
			"averageBudgetProposedFineDishesByCurrency", "averageBudgetAcceptedFineDishesByCurrency", "averageBudgetDeniedFineDishesByCurrency",
			"deviationBudgetProposedFineDishesByCurrency", "deviationBudgetAcceptedFineDishesByCurrency", "deviationBudgetDeniedFineDishesByCurrency",
			"minBudgetProposedFineDishesByCurrency", "minBudgetAcceptedFineDishesByCurrency", "minBudgetDeniedFineDishesByCurrency",
			"maxBudgetProposedFineDishesByCurrency", "maxBudgetAcceptedFineDishesByCurrency", "maxBudgetDeniedFineDishesByCurrency");
	}

}
