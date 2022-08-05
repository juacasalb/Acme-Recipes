package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository{

	@Query("SELECT fd FROM FineDish fd WHERE fd.epicure.id = :epicureId")
	Collection<FineDish> findEpicureDishesByEpicureId(int epicureId);

	@Query("SELECT fd FROM FineDish fd WHERE fd.id = :dishId")
	FineDish findFineDishByDishId(int dishId);

}
