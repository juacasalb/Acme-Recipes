package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EpicureDashboard implements Serializable{

private static final long serialVersionUID = 1L;
	
	
	Integer totalNProposedFineDishes;
	Integer totalNAcceptedFineDishes;
	Integer totalNDeniedFineDishes;
	

	Map<String,Double> averageBudgetProposedFineDishesByCurrency;
	Map<String,Double> averageBudgetAcceptedFineDishesByCurrency;
	Map<String,Double> averageBudgetDeniedFineDishesByCurrency;
	
	Map<String,Double> deviationBudgetProposedFineDishesByCurrency;
	Map<String,Double> deviationBudgetAcceptedFineDishesByCurrency;
	Map<String,Double> deviationBudgetDeniedFineDishesByCurrency;
	
	Map<String,Double> minBudgetProposedFineDishesByCurrency;
	Map<String,Double> minBudgetAcceptedFineDishesByCurrency;
	Map<String,Double> minBudgetDeniedFineDishesByCurrency;
	
	Map<String,Double> maxBudgetProposedFineDishesByCurrency;
	Map<String,Double> maxBudgetAcceptedFineDishesByCurrency;
	Map<String,Double> maxBudgetDeniedFineDishesByCurrency;
}
