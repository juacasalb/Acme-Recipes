package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdminDashboardRepository extends AbstractRepository{

	
	@Query("select count(i) from Item i where i.type=0")
	int totalNIngredients();
	@Query("select count(i) from Item i where i.type=1")
	int totalNKitchenUtensils();
	@Query("select fd.state, count(fd) from FineDish fd group by fd.state")
	List<Object[]> totalNDishesByStatus();
	
	//average
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> averageRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> averageRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, avg(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> averageBudgetDishesByStatus();
	
	//deviation
	
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, stddev(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> deviationBudgetDishesByStatus();
	
	//minimum
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, min(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> minBudgetDishesByStatus();
	
	//maximum
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, max(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> maxBudgetDishesByStatus();
	
	//CC

	//average
	@Query("select x.allotment.currency, avg(x.allotment.amount) from Ketema x group by x.allotment.currency")
	List<Object[]> averageBudgetByCurrency();

	//deviation
	@Query("select x.allotment.currency, stddev(x.allotment.amount) from Ketema x group by x.allotment.currency")
	List<Object[]> deviationBudgetByCurrency();

	//minimum
	@Query("select x.allotment.currency, min(x.allotment.amount) from Ketema x group by x.allotment.currency")
	List<Object[]> minBudgetByCurrency();

	//maximum
	@Query("select x.allotment.currency, max(x.allotment.amount) from Ketema x group by x.allotment.currency")
	List<Object[]> maxBudgetByCurrency();
		
}
