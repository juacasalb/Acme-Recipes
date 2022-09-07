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
	
	@Query("select count(p) from Pimpam p")
	long countPimpam();
	@Query("select count(it) from Item it")
	long countItem();
	
	
	//average
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> averageRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> averageRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, avg(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> averageBudgetDishesByStatus();
	@Query("select p.budget.currency, avg(p.budget.amount) from Pimpam p group by p.budget.currency")
	List<Object[]> averageBudgetPimpampsByCurrency();
	
	//deviation
	
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, stddev(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> deviationBudgetDishesByStatus();
	@Query("select p.budget.currency, stddev(p.budget.amount) from Pimpam p group by p.budget.currency")
	List<Object[]> deviationBudgetPimpampsByCurrency();
	
	//minimum
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, min(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> minBudgetDishesByStatus();
	@Query("select p.budget.currency, min(p.budget.amount) from Pimpam p group by p.budget.currency")
	List<Object[]> minimumBudgetPimpampsByCurrency();
	
	
	//maximum
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.budget.currency, max(fd.budget.amount) from FineDish fd group by fd.state, fd.budget.currency")
	List<Object[]> maxBudgetDishesByStatus();
	@Query("select p.budget.currency, max(p.budget.amount) from Pimpam p group by p.budget.currency")
	List<Object[]> maximumBudgetPimpampsByCurrency();
	
}
