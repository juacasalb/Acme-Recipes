//package acme.features.epicure.quittel;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import acme.entities.Item;
//import acme.entities.Quittel;
//import acme.framework.repositories.AbstractRepository;
//import acme.roles.Epicure;
//
//@Repository
//public interface EpicureQuittelRepository extends AbstractRepository{
//
//	@Query("SELECT x FROM Quittel x WHERE x.id = :id")
//	Quittel findQuittelById(int id);
//	
//	@Query("SELECT x FROM Quittel x WHERE x.code = :code")
//	Quittel findQuittelByCode(String code);
//	
//	@Query("SELECT x FROM Quittel x WHERE x.item.epicure.id = :id")
//	Collection<Quittel> findQuittelsByEpicureId(int id);
//	
//	@Query("SELECT c FROM Epicure c WHERE c.id=:id")
//	Epicure findEpicureById(int id);
//	
//	@Query("SELECT x FROM Quittel x")
//	List<Quittel> findAllQuittels();
//	
//	@Query("SELECT c FROM Epicure c")
//	List<Epicure> findAllEpicures();
//	
//	@Query("SELECT i FROM Item i")
//	List<Item> findAllItems();
//
//	@Query("SELECT x FROM Quittel x where x.instantationMoment > :deadline")
//	Collection<Quittel> findRecentQuittel(Date deadline);
//
//	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null AND x.item.type = 0")
//	List<Item> findIngredientWithQuittel();
//
//	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null AND x.item.type = 1")
//	List<Item> findKitchenUtensilWithQuittel();
//	
//	@Query("SELECT x.item FROM Quittel x WHERE x.item is not null")
//	List<Item> findItemsWithQuittel();
//	
//	@Query("select i from Item i where i.published = true and i.epicure.id = :epicureId")
//	Collection<Item> findManyAvailableItemsByEpicure(int epicureId);
//}
