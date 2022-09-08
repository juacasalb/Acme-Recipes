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
	@Query("select fd.state, fd.helping.currency, avg(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
	List<Object[]> averageHelpingDishesByStatus();
	
	//deviation
	
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.helping.currency, stddev(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
	List<Object[]> deviationHelpingDishesByStatus();
	
	//minimum
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> minRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.helping.currency, min(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
	List<Object[]> minHelpingDishesByStatus();
	
	//maximum
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=0 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceIngredientsByCurrency();
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i where i.type=1 group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceKitchenUtensilsByCurrency();
	@Query("select fd.state, fd.helping.currency, max(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
	List<Object[]> maxHelpingDishesByStatus();
	
	//CC

	//average
	@Query("select x.helping.currency, avg(x.helping.amount) from Quittel x group by x.helping.currency")
	List<Object[]> averageHelpingByCurrency();

	//deviation
	@Query("select x.helping.currency, stddev(x.helping.amount) from Quittel x group by x.helping.currency")
	List<Object[]> deviationHelpingByCurrency();

	//minimum
	@Query("select x.helping.currency, min(x.helping.amount) from Quittel x group by x.helping.currency")
	List<Object[]> minHelpingByCurrency();

	//maximum
	@Query("select x.helping.currency, max(x.helping.amount) from Quittel x group by x.helping.currency")
	List<Object[]> maxHelpingByCurrency();
		
}
