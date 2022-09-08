package acme.features.chef.pimpam;

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
public interface ChefPimpamRepository extends AbstractRepository{

	@Query("SELECT x FROM Pimpam x WHERE x.id = :id")
	Pimpam findPimpamById(int id);
	
	@Query("SELECT x FROM Pimpam x WHERE x.code = :code")
	Pimpam findPimpamByCode(String code);
	
	@Query("SELECT x FROM Pimpam x WHERE x.item.chef.id = :id")
	Collection<Pimpam> findPimpamsByChefId(int id);

	@Query("SELECT c FROM Chef c WHERE c.id=:id")
	Chef findChefById(int id);
	
	@Query("SELECT x FROM Pimpam x")
	List<Pimpam> findAllPimpams();
	
	@Query("SELECT c FROM Chef c")
	List<Chef> findAllChefs();
	
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
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i not in (select x.item from Pimpam x)")
	Collection<Item> findManyAvailableItemsByChef(int chefId);
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i.type = 0 and i not in (select x.item from Pimpam x)")
	Collection<Item> findManyAvailableIngredientsByChef(int chefId);
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i.type = 1 and i not in (select x.item from Pimpam x)")
	Collection<Item> findManyAvailableKitUtensilByChef(int chefId);

}
