package acme.features.administrator.dashboard;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.fineDish.State;
import acme.features.any.item.AnyItemRepository;
import acme.features.chef.quittel.ChefQuittelRepository;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	@Autowired
	protected AdminDashboardRepository repository;
	
	@Autowired
	protected AnyItemRepository itemRepository;


	//si es chef
	@Autowired
	protected ChefQuittelRepository quittelRepository;

	//si es epicure
//	@Autowired
//	protected EpicureQuittelRepository quittelRepository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		final AdministratorDashboard adminDashboard = new AdministratorDashboard();
		final Integer totalNIngredients;
		final Integer totalNKitchenUtensils;
		final Map<State, Integer> totalNDishesByStatus;
		
		final Map<String,Double> averageRetailPriceIngredientsByCurrency;
		final Map<String,Double> averageRetailPriceKitchenUtensilsByCurrency;
		final Map<Pair<State, String>, Double> averageHelpingDishesByStatus;
		
		final Map<String,Double> deviationRetailPriceIngredientsByCurrency;
		final Map<String,Double> deviationRetailPriceKitchenUtensilsByCurrency;
		final Map<Pair<State, String>, Double> deviationHelpingDishesByStatus;
		
		final Map<String,Double> minRetailPriceIngredientsByCurrency;
		final Map<String,Double> minRetailPriceKitchenUtensilsByCurrency;
		final Map<Pair<State, String>, Double> minHelpingDishesByStatus;
		
		final Map<String,Double> maxRetailPriceIngredientsByCurrency;
		final Map<String,Double> maxRetailPriceKitchenUtensilsByCurrency;
		final Map<Pair<State, String>, Double> maxHelpingDishesByStatus;
		
		totalNIngredients = this.repository.totalNIngredients();
		totalNKitchenUtensils = this.repository.totalNKitchenUtensils();
		totalNDishesByStatus = this.repository.totalNDishesByStatus().stream().collect(Collectors.toMap(x-> (State)x[0],x->((Long)x[1]).intValue()));
		
		averageRetailPriceIngredientsByCurrency = this.repository.averageRetailPriceIngredientsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageRetailPriceKitchenUtensilsByCurrency = this.repository.averageRetailPriceKitchenUtensilsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageHelpingDishesByStatus = this.repository.averageHelpingDishesByStatus().stream()
			.collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		deviationRetailPriceIngredientsByCurrency = this.repository.deviationRetailPriceIngredientsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationRetailPriceKitchenUtensilsByCurrency = this.repository.deviationRetailPriceKitchenUtensilsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationHelpingDishesByStatus = this.repository.deviationHelpingDishesByStatus().stream()
			.collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		minRetailPriceIngredientsByCurrency = this.repository.minRetailPriceIngredientsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minRetailPriceKitchenUtensilsByCurrency = this.repository.minRetailPriceKitchenUtensilsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minHelpingDishesByStatus = this.repository.minHelpingDishesByStatus().stream()
			.collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		maxRetailPriceIngredientsByCurrency = this.repository.maxRetailPriceIngredientsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maxRetailPriceKitchenUtensilsByCurrency = this.repository.maxRetailPriceKitchenUtensilsByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maxHelpingDishesByStatus = this.repository.maxHelpingDishesByStatus().stream()
			.collect(Collectors.toMap(x->Pair.of((State)x[0], (String)x[1]), x->(Double) x[2]));
		
		
		adminDashboard.setTotalNIngredients(totalNIngredients);
		adminDashboard.setTotalNKitchenUtensils(totalNKitchenUtensils);
		adminDashboard.setTotalNDishesByStatus(totalNDishesByStatus);
		adminDashboard.setAverageRetailPriceIngredientsByCurrency(averageRetailPriceIngredientsByCurrency);
		adminDashboard.setAverageRetailPriceKitchenUtensilsByCurrency(averageRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setAverageHelpingDishesByStatus(averageHelpingDishesByStatus);
		adminDashboard.setDeviationRetailPriceIngredientsByCurrency(deviationRetailPriceIngredientsByCurrency);
		adminDashboard.setDeviationRetailPriceKitchenUtensilsByCurrency(deviationRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setDeviationHelpingDishesByStatus(deviationHelpingDishesByStatus);
		adminDashboard.setMinRetailPriceIngredientsByCurrency(minRetailPriceIngredientsByCurrency);
		adminDashboard.setMinRetailPriceKitchenUtensilsByCurrency(minRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setMinHelpingDishesByStatus(minHelpingDishesByStatus);
		adminDashboard.setMaxRetailPriceIngredientsByCurrency(maxRetailPriceIngredientsByCurrency);
		adminDashboard.setMaxRetailPriceKitchenUtensilsByCurrency(maxRetailPriceKitchenUtensilsByCurrency);
		adminDashboard.setMaxHelpingDishesByStatus(maxHelpingDishesByStatus);
		
		
		//CC -------------------------------------------------------------------------------------------------------------

		//si es Ingredient
//		final Double ratio = ((double)this.quittelRepository.findIngredientWithQuittel().size()/(double)this.itemRepository.findAllIngredients().size());

		//si es kitchenUtensil
		final Double ratio = ((double)this.quittelRepository.findKitchenUtensilWithQuittel().size()/(double)this.itemRepository.findAllKitchenUtensils().size());
		
//		//si es item
//		final Double ratio = ((double)this.quittelRepository.findItemsWithQuittel().size()/(double)this.quittelRepository.findAllItems().size());

		final Map<String,Double> averageHelpingByCurrency;
		final Map<String,Double> deviationHelpingByCurrency;
		final Map<String,Double> minHelpingByCurrency;
		final Map<String,Double> maxHelpingByCurrency;

		averageHelpingByCurrency = this.repository.averageHelpingByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));

		deviationHelpingByCurrency = this.repository.deviationHelpingByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));

		minHelpingByCurrency = this.repository.minHelpingByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));

		maxHelpingByCurrency = this.repository.maxHelpingByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));

		adminDashboard.setRatio(ratio);
		adminDashboard.setAverageHelpingByCurrency(averageHelpingByCurrency);
		adminDashboard.setDeviationHelpingByCurrency(deviationHelpingByCurrency);
		adminDashboard.setMinHelpingByCurrency(minHelpingByCurrency);
		adminDashboard.setMaxHelpingByCurrency(maxHelpingByCurrency);

		//----------------------------------------------------------------------------------------------------------------
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
			"averageRetailPriceIngredientsByCurrency",
			"averageRetailPriceKitchenUtensilsByCurrency","averageHelpingDishesByStatus",
			"deviationRetailPriceIngredientsByCurrency","deviationRetailPriceKitchenUtensilsByCurrency","deviationHelpingDishesByStatus",
			"minRetailPriceIngredientsByCurrency","minRetailPriceKitchenUtensilsByCurrency","minHelpingDishesByStatus",
			"maxRetailPriceIngredientsByCurrency","maxRetailPriceKitchenUtensilsByCurrency","maxHelpingDishesByStatus",
			"ratio", "averageHelpingByCurrency", "deviationHelpingByCurrency", "minHelpingByCurrency", "maxHelpingByCurrency");
	}

}
