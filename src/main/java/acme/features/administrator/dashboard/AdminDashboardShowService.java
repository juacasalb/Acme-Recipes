package acme.features.administrator.dashboard;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.State;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	@Autowired
	protected AdminDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		final AdministratorDashboard adminDashboard = new AdministratorDashboard();
		
		Integer totalNIngredients;
		Integer totalNKitchenUtensils;
		Map<State, Integer> totalNDishesByStatus;
		
		Map<String,Double> averageRetailPriceIngredientsByCurrency, deviationRetailPriceIngredientsByCurrency, minRetailPriceIngredientsByCurrency, maxRetailPriceIngredientsByCurrency,
		averageRetailPriceKitchenUtensilsByCurrency,deviationRetailPriceKitchenUtensilsByCurrency,minRetailPriceKitchenUtensilsByCurrency,maxRetailPriceKitchenUtensilsByCurrency;
		
		
		
		Map<Pair<State, String>, Double> averageBudgetDishesByStatus,deviationBudgetDishesByStatus,minBudgetDishesByStatus,maxBudgetDishesByStatus;
		
		//Metricas
		
		totalNIngredients = this.repository.totalNIngredients();
		totalNKitchenUtensils = this.repository.totalNKitchenUtensils();
		totalNDishesByStatus = this.repository.totalNDishesByStatus().stream().collect(Collectors.toMap(x-> (State)x[0],x->((Long)x[1]).intValue()));
		
		//Average
		averageRetailPriceIngredientsByCurrency = this.repository.averageRetailPriceIngredientsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageRetailPriceKitchenUtensilsByCurrency = this.repository.averageRetailPriceKitchenUtensilsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageBudgetDishesByStatus = this.repository.averageBudgetDishesByStatus().stream().collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		//Standard Deviation
		deviationRetailPriceIngredientsByCurrency = this.repository.deviationRetailPriceIngredientsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationRetailPriceKitchenUtensilsByCurrency = this.repository.deviationRetailPriceKitchenUtensilsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationBudgetDishesByStatus = this.repository.deviationBudgetDishesByStatus().stream().collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		//Minimum
		minRetailPriceIngredientsByCurrency = this.repository.minRetailPriceIngredientsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minRetailPriceKitchenUtensilsByCurrency = this.repository.minRetailPriceKitchenUtensilsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minBudgetDishesByStatus = this.repository.minBudgetDishesByStatus().stream().collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		//Maximum
		maxRetailPriceIngredientsByCurrency = this.repository.maxRetailPriceIngredientsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maxRetailPriceKitchenUtensilsByCurrency = this.repository.maxRetailPriceKitchenUtensilsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maxBudgetDishesByStatus = this.repository.maxBudgetDishesByStatus().stream().collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		
		
		adminDashboard.setTotalNIngredients(totalNIngredients);
		adminDashboard.setTotalNKitchenUtensils(totalNKitchenUtensils);
		adminDashboard.setTotalNDishesByStatus(totalNDishesByStatus);
		
		adminDashboard.setAverageRetailPriceIngredientsByCurrency(averageRetailPriceIngredientsByCurrency);
		adminDashboard.setAverageRetailPriceKitchenUtensilsByCurrency(averageRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setAverageBudgetDishesByStatus(averageBudgetDishesByStatus);
		
		adminDashboard.setDeviationRetailPriceIngredientsByCurrency(deviationRetailPriceIngredientsByCurrency);
		adminDashboard.setDeviationRetailPriceKitchenUtensilsByCurrency(deviationRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setDeviationBudgetDishesByStatus(deviationBudgetDishesByStatus);
		
		adminDashboard.setMinRetailPriceIngredientsByCurrency(minRetailPriceIngredientsByCurrency);
		adminDashboard.setMinRetailPriceKitchenUtensilsByCurrency(minRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setMinBudgetDishesByStatus(minBudgetDishesByStatus);
		
		adminDashboard.setMaxRetailPriceIngredientsByCurrency(maxRetailPriceIngredientsByCurrency);
		adminDashboard.setMaxRetailPriceKitchenUtensilsByCurrency(maxRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setMaxBudgetDishesByStatus(maxBudgetDishesByStatus);
		
		
		
		
		//Pimpam
		Double pimpamRatio;
		Map<String,Double> averageBudgetPimpampsByCurrency, deviationBudgetPimpampsByCurrency, minimumBudgetPimpampsByCurrency, maximumBudgetPimpampsByCurrency;
		
		pimpamRatio = 100.0*this.repository.countPimpam()/this.repository.countItem();
		
		averageBudgetPimpampsByCurrency = this.repository.averageBudgetPimpampsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationBudgetPimpampsByCurrency = this.repository.deviationBudgetPimpampsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minimumBudgetPimpampsByCurrency = this.repository.minimumBudgetPimpampsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maximumBudgetPimpampsByCurrency = this.repository.maximumBudgetPimpampsByCurrency().stream().collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		
		adminDashboard.setPimpamRatio(pimpamRatio);
		adminDashboard.setAverageBudgetPimpampsByCurrency(averageBudgetPimpampsByCurrency);
		adminDashboard.setDeviationBudgetPimpampsByCurrency(deviationBudgetPimpampsByCurrency);
		adminDashboard.setMinimumBudgetPimpampsByCurrency(minimumBudgetPimpampsByCurrency);
		adminDashboard.setMaximumBudgetPimpampsByCurrency(maximumBudgetPimpampsByCurrency);
		
		return adminDashboard;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
				
		request.unbind(entity, model, "totalNIngredients",
			"totalNKitchenUtensils",
			"totalNDishesByStatus",
			"pimpamRatio",
			"averageRetailPriceIngredientsByCurrency",
			"averageRetailPriceKitchenUtensilsByCurrency","averageBudgetDishesByStatus","averageBudgetPimpampsByCurrency",
			"deviationRetailPriceIngredientsByCurrency","deviationRetailPriceKitchenUtensilsByCurrency","deviationBudgetDishesByStatus","deviationBudgetPimpampsByCurrency",
			"minRetailPriceIngredientsByCurrency","minRetailPriceKitchenUtensilsByCurrency","minBudgetDishesByStatus","minimumBudgetPimpampsByCurrency",
			"maxRetailPriceIngredientsByCurrency","maxRetailPriceKitchenUtensilsByCurrency","maxBudgetDishesByStatus","maximumBudgetPimpampsByCurrency");
	}

}
