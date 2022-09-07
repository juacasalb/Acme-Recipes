package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.fineDish.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdministratorDashboard implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	Integer totalNIngredients;
	Integer totalNKitchenUtensils;
	Map<State, Integer> totalNDishesByStatus;
	

	Map<String,Double> averageRetailPriceIngredientsByCurrency;
	Map<String,Double> averageRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> averageBudgetDishesByStatus;
	
	Map<String,Double> deviationRetailPriceIngredientsByCurrency;
	Map<String,Double> deviationRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> deviationBudgetDishesByStatus;
	
	Map<String,Double> minRetailPriceIngredientsByCurrency;
	Map<String,Double> minRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> minBudgetDishesByStatus;
	
	Map<String,Double> maxRetailPriceIngredientsByCurrency;
	Map<String,Double> maxRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> maxBudgetDishesByStatus;
	
	//CC

	Double ratio;
	Map<String,Double> averageBudgetByCurrency;
	Map<String,Double> deviationBudgetByCurrency;
	Map<String,Double> minBudgetByCurrency;
	Map<String,Double> maxBudgetByCurrency;
}
