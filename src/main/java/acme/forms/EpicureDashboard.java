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
	
	Map<String, Long> totalNDishesOfState;

	Map<Pair<String, String>,Double> averageHelpingFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> deviationHelpingFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> minHelpingFineDishesOfStateByCurrency;
	Map<Pair<String, String>,Double> maxHelpingFineDishesOfStateByCurrency;
  
}
