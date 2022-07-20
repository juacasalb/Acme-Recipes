package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdministratorDashboard implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	Integer totalNIngredients;
	Integer totalNKitchenUtensils;
	Integer totalNDishesByStatus;
	

	Map<Pair<String,String>,Double> averageRetailPriceIngredientsByCurrency;
	Map<String,Double> averageRetailPriceKitchenUtensilsByCurrency;
	Map<String,Double> averageBudgetDishesByStatus;
	
	Map<Pair<String,String>,Double> deviationRetailPriceIngredientsByCurrency;
	Map<String,Double> deviationRetailPriceKitchenUtensilsByCurrency;
	Map<String,Double> deviationBudgetDishesByStatus;
	
	Map<Pair<String,String>,Double> minRetailPriceIngredientsByCurrency;
	Map<String,Double> minRetailPriceKitchenUtensilsByCurrency;
	Map<String,Double> minBudgetDishesByStatus;
	
	Map<Pair<String,String>,Double> maxRetailPriceIngredientsByCurrency;
	Map<String,Double> maxRetailPriceKitchenUtensilsByCurrency;
	Map<String,Double> maxBudgetDishesByStatus;
}