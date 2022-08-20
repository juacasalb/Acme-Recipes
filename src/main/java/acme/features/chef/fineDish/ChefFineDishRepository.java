package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.State;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefFineDishRepository extends AbstractRepository {

	@Query("SELECT fd FROM FineDish fd WHERE fd.chef.id = :chefId AND fd.published = true")
	Collection<FineDish> findEpicureDishesByEpicureId(int chefId);

	@Query("SELECT fd FROM FineDish fd WHERE fd.id = :dishId")
	FineDish findFineDishByDishId(int dishId);

	@Query("SELECT fd FROM FineDish fd WHERE fd.chef.id = :chefId AND fd.state = :state AND fd.published = true")
	Collection<FineDish> findDishesByChefIdAndState(int chefId, State state);
	

	
}
