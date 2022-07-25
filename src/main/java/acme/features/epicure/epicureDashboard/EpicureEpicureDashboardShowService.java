package acme.features.epicure.epicureDashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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
		Long count;
		
		Map<Pair<String, String>, Double> valueMap;
		Map<String, Long> countMap;
		countMap = new HashMap<>();
		
		EpicureDashboard result;
		List<Object[]> fineDishesList;
		
		final Integer epicureId = request.getPrincipal().getActiveRoleId();
		
		
		fineDishesList = this.repository.countFineDishesOfState(epicureId);
		result = new EpicureDashboard();
		for (final Object[] o: fineDishesList) {
			s = (State) o[0];
			count = (Long) o[1];
			countMap.put(s.toString(), count);
		}
		result.setTotalNDishesOfState(countMap);
		
		//Average budgets
		fineDishesList = this.repository.averageBudgetFineDishesOfStateByCurrency(epicureId);
		valueMap = this.getMapFromList(fineDishesList);
		result.setAverageBudgetFineDishesOfStateByCurrency(valueMap);
		
		//Deviation budgets
		fineDishesList = this.repository.deviationBudgetFineDishesOfStateByCurrency(epicureId);
		valueMap = this.getMapFromList(fineDishesList);
		result.setDeviationBudgetFineDishesOfStateByCurrency(valueMap);
		
		//Min budget
		fineDishesList = this.repository.minBudgetFineDishesOfStateByCurrency(epicureId);
		valueMap = this.getMapFromList(fineDishesList);
		result.setMinBudgetFineDishesOfStateByCurrency(valueMap);
		
		//Max budget
		fineDishesList = this.repository.maxBudgetFineDishesOfStateByCurrency(epicureId);
		valueMap = this.getMapFromList(fineDishesList);
		result.setMaxBudgetFineDishesOfStateByCurrency(valueMap);
		
		return result;
	}
	
	private Map<Pair<String,String>,Double> getMapFromList(final List<Object[]> list){
		Map<Pair<String,String>,Double> result;
		result = new HashMap<>();
		
		State state;
		String currency;
		Pair<String,String> key;
		Double value;
		
		for(final Object[] o : list) {
			state = (State) o[0];
			currency = (String) o[1];
			value = (Double) o[2];
			key = Pair.of(state.toString(), currency);
			result.put(key, value);
		}
		return result;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
				
		request.unbind(entity, model, "totalNDishesOfState",
			"averageBudgetFineDishesOfStateByCurrency",
			"deviationBudgetFineDishesOfStateByCurrency",
			"minBudgetFineDishesOfStateByCurrency",
			"maxBudgetFineDishesOfStateByCurrency");
	}

}
