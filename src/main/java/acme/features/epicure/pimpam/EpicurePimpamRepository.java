package acme.features.epicure.pimpam;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface EpicurePimpamRepository extends AbstractRepository{

	@Query("SELECT x FROM Pimpam x WHERE x.id = :id")
	Pimpam findPimpamById(int id);
	
	@Query("SELECT x FROM Pimpam x WHERE x.item.chef.id = :id")
	Collection<Pimpam> findPimpamsByInventorId(int id);
	
	@Query("SELECT e FROM Epicure e WHERE e.id=:id")
	Chef findEpicureById(int id);
	
	@Query("SELECT x FROM Pimpam x")
	List<Pimpam> findAllPimpams();
	
	@Query("SELECT e FROM Epicure e")
	List<Chef> findAllEpicures();
	
	@Query("SELECT i FROM Item i")
	List<Item> findAllItems();

	@Query("SELECT x FROM Pimpam x where x.instantationMoment > :deadline")
	Collection<Pimpam> findRecentPimpam(Date deadline);

	@Query("SELECT x.item FROM Pimpam x WHERE x.item is not null AND x.item.type = 0")
	List<Item> findIngredientWithPimpam();

	@Query("SELECT x.item FROM Pimpam x WHERE x.item is not null AND x.item.type = 1")
	List<Item> findKitchenUtensilWithPimpam();
	
	@Query("SELECT x.item FROM Pimpam x WHERE x.item is not null")
	List<Item> findItemsWithPimpam();
}
