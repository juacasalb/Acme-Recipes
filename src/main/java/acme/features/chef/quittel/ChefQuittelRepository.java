package acme.features.chef.quittel;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Quittel;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefQuittelRepository extends AbstractRepository{

	@Query("SELECT x FROM Quittel x WHERE x.id = :id")
	Quittel findQuittelById(int id);
	
	@Query("SELECT x FROM Quittel x WHERE x.code = :code")
	Quittel findQuittelByCode(String code);
	
	@Query("SELECT x FROM Quittel x WHERE x.item.chef.id = :id")
	Collection<Quittel> findQuittelsByChefId(int id);
	
	@Query("SELECT c FROM Chef c WHERE c.id=:id")
	Chef findChefById(int id);
	
	@Query("SELECT x FROM Quittel x")
	List<Quittel> findAllQuittels();
	
	@Query("SELECT c FROM Chef c")
	List<Chef> findAllChefs();
	
	@Query("SELECT i FROM Item i")
	List<Item> findAllItems();

	@Query("SELECT x FROM Quittel x where x.instantationMoment > :deadline")
	Collection<Quittel> findRecentQuittel(Date deadline);

	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null AND x.item.type = 0")
	List<Item> findIngredientWithQuittel();

	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null AND x.item.type = 1")
	List<Item> findKitchenUtensilWithQuittel();
	
	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null")
	List<Item> findItemsWithQuittel();
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i not in (select x.item from Quittel x)")
	Collection<Item> findManyAvailableItemsByChef(int chefId);
	
	@Query("SELECT a FROM Item a WHERE a.published = false AND a.chef.id = :id AND a.type = 0 AND a not in (select x.item from Quittel x)")
	Collection<Item> findIngredientsByChefId(int id);
	
	@Query("SELECT a FROM Item a WHERE a.published = false AND a.chef.id = :id AND a.type = 1 AND a not in (select x.item from Quittel x)")
	Collection<Item> findKitchenUtensilsByChefId(int id);

}
