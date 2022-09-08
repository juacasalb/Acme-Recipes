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
	Map<Pair<State, String>, Double> averageHelpingDishesByStatus;
	
	Map<String,Double> deviationRetailPriceIngredientsByCurrency;
	Map<String,Double> deviationRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> deviationHelpingDishesByStatus;
	
	Map<String,Double> minRetailPriceIngredientsByCurrency;
	Map<String,Double> minRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> minHelpingDishesByStatus;
	
	Map<String,Double> maxRetailPriceIngredientsByCurrency;
	Map<String,Double> maxRetailPriceKitchenUtensilsByCurrency;
	Map<Pair<State, String>, Double> maxHelpingDishesByStatus;
	
	//CC

	Double ratio;
	Map<String,Double> averageHelpingByCurrency;
	Map<String,Double> deviationHelpingByCurrency;
	Map<String,Double> minHelpingByCurrency;
	Map<String,Double> maxHelpingByCurrency;
}
