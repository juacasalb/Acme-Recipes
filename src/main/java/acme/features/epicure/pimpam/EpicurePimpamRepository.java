package acme.features.epicure.pimpam;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface EpicurePimpamRepository extends AbstractRepository{

	@Query("SELECT x FROM Pimpam x WHERE x.id = :id")
	Pimpam findPimpamById(int id);
	
	@Query("SELECT x FROM Pimpam x WHERE x.code = :code")
	Pimpam findPimpamByCode(String code);
	
	@Query("SELECT x FROM Pimpam x WHERE x.item.epicure.id = :id")
	Collection<Pimpam> findPimpamsByEpicureId(int id);
	
	@Query("SELECT c FROM Epicure c WHERE c.id=:id")
	Epicure findEpicureById(int id);
	
	@Query("SELECT x FROM Pimpam x")
	List<Pimpam> findAllPimpams();
	
	@Query("SELECT c FROM Epicure c")
	List<Epicure> findAllEpicures();
	
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
	
	@Query("select i from Item i where i.published = true and i.epicure.id = :epicureId")
	Collection<Item> findManyAvailableItemsByEpicure(int epicureId);
}
