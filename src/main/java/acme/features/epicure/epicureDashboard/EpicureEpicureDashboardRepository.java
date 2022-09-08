package acme.features.epicure.epicureDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureEpicureDashboardRepository extends AbstractRepository {
	
	@Query("select fd.state, count(fd) from FineDish fd where fd.epicure.id =:epicureId group by fd.state")
	List<Object[]> countFineDishesOfState(int epicureId);
	@Query("select fd.state, fd.helping.currency, avg(fd.helping.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.helping.currency")
	List<Object[]> averageHelpingFineDishesOfStateByCurrency(int epicureId);
	@Query("select fd.state, fd.helping.currency, stddev(fd.helping.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.helping.currency")
	List<Object[]> deviationHelpingFineDishesOfStateByCurrency(int epicureId);
	@Query("select fd.state, fd.helping.currency, min(fd.helping.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.helping.currency")
	List<Object[]> minHelpingFineDishesOfStateByCurrency(int epicureId);
	@Query("select fd.state, fd.helping.currency, max(fd.helping.amount) from FineDish fd where fd.epicure.id =:epicureId group by fd.state, fd.helping.currency")
	List<Object[]> maxHelpingFineDishesOfStateByCurrency(int epicureId);
	
	
//	@Query("select fd.state, count(fd) from FineDish fd group by fd.state")
//	List<Object[]> countFineDishesOfState(int epicureId);
//	
//	@Query("select fd.state, fd.helping.currency, avg(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
//	List<Object[]> averageHelpingFineDishesOfStateByCurrency(int epicureId);
//	
//	@Query("select fd.state, fd.helping.currency, stddev(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
//	List<Object[]> deviationHelpingFineDishesOfStateByCurrency(int epicureId);
//	
//	@Query("select fd.state, fd.helping.currency, min(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
//	List<Object[]> minHelpingFineDishesOfStateByCurrency(int epicureId);
//	
//	@Query("select fd.state, fd.helping.currency, max(fd.helping.amount) from FineDish fd group by fd.state, fd.helping.currency")
//	List<Object[]> maxHelpingFineDishesOfStateByCurrency(int epicureId);
}
