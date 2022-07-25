package acme.features.epicure.epicureDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureEpicureDashboardRepository extends AbstractRepository {
	
	@Query("select fd.state, count(fd) from FineDish fd where fd.epicure.id =:epicureId group by fd.state")
	List<Object[]> countFineDishesOfState(int epicureId);
	
	@Query("select fd.state, fd.budget.currency, avg(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.budget.currency")
	List<Object[]> averageBudgetFineDishesOfStateByCurrency(int epicureId);
	
	@Query("select fd.state, fd.budget.currency, stddev(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.budget.currency")
	List<Object[]> deviationBudgetFineDishesOfStateByCurrency(int epicureId);
	
	@Query("select fd.state, fd.budget.currency, min(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.budget.currency")
	List<Object[]> minBudgetFineDishesOfStateByCurrency(int epicureId);
	
	@Query("select fd.state, fd.budget.currency, max(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.budget.currency")
	List<Object[]> maxBudgetFineDishesOfStateByCurrency(int epicureId);
}
