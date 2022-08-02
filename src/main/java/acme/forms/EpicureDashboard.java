package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EpicureDashboard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Map<String, Integer> totalNDishesOfState;

	Map<Pair<String, String>,Double> averageBudgetFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> deviationBudgetFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> minBudgetFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> maxBudgetFineDishesOfStateByCurrency;
}
