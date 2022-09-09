package acme.features.chef.ketema;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Ketema;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefKetemaRepository extends AbstractRepository{

	@Query("SELECT x FROM Ketema x WHERE x.id = :id")
	Ketema findKetemaById(int id);
	
	@Query("SELECT x FROM Ketema x WHERE x.keylet = :code")
	Ketema findKetemaByCode(String code);
	
	@Query("SELECT x FROM Ketema x WHERE x.item.chef.id = :id")
	Collection<Ketema> findKetemasByChefId(int id);

	@Query("SELECT c FROM Chef c WHERE c.id=:id")
	Chef findChefById(int id);
	
	@Query("SELECT x FROM Ketema x")
	List<Ketema> findAllKetemas();
	
	@Query("SELECT c FROM Chef c")
	List<Chef> findAllChefs();
	
	@Query("SELECT i FROM Item i")
	List<Item> findAllItems();

	@Query("SELECT x FROM Ketema x where x.instantationMoment > :deadline")
	Collection<Ketema> findRecentKetema(Date deadline);

	@Query("SELECT x.item FROM Ketema x WHERE x.item is not null AND x.item.type = 0")
	List<Item> findIngredientWithKetema();

	@Query("SELECT x.item FROM Ketema x WHERE x.item is not null AND x.item.type = 1")
	List<Item> findKitchenUtensilWithKetema();
	
	@Query("SELECT x.item FROM Ketema x WHERE x.item is not null")
	List<Item> findItemsWithKetema();
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i not in (select x.item from Ketema x)")
	Collection<Item> findManyAvailableItemsByChef(int chefId);
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i.type = 0 and i not in (select x.item from Ketema x)")
	Collection<Item> findManyAvailableIngredientsByChef(int chefId);
	
	@Query("select i from Item i where i.published = false and i.chef.id = :chefId and i.type = 1 and i not in (select x.item from Ketema x)")
	Collection<Item> findManyAvailableKitUtensilByChef(int chefId);

}
