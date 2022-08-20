package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDish.FineDish;
import acme.entities.fineDish.State;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;
import acme.system.configuration.CurrencyConfiguration;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository{

	@Query("SELECT fd FROM FineDish fd WHERE fd.epicure.id = :epicureId")
	Collection<FineDish> findEpicureDishesByEpicureId(int epicureId);

	@Query("SELECT fd FROM FineDish fd WHERE fd.id = :dishId")
	FineDish findFineDishByDishId(int dishId);

	@Query("SELECT e FROM Epicure e WHERE e.id = :epicureId")
	Epicure findById(int epicureId);

	@Query("SELECT c FROM Chef c WHERE c.userAccount.username = :chefId")
	Chef findChefByUsername(String chefId);

	@Query("SELECT cc.acceptedCurrencies FROM CurrencyConfiguration cc")
	String acceptedCurrencies();

	@Query("SELECT cc FROM CurrencyConfiguration cc")
	CurrencyConfiguration findCurrencyConiguration();
	
	@Query("SELECT fd FROM FineDish fd WHERE fd.code = :code")
	FineDish findDishByCode(String code);

	@Query("SELECT fd FROM FineDish fd WHERE fd.epicure.id = :epicureId AND fd.published = false")
	Collection<FineDish> findNotPublishedDishesByEpicureId(int epicureId);
	
	@Query("SELECT fd FROM FineDish fd WHERE fd.epicure.id = :epicureId AND fd.published = true")
	Collection<FineDish> findPublishedDishesByEpicureId(int epicureId);

	@Query("SELECT fd FROM FineDish fd WHERE fd.epicure.id = :epicureId AND fd.state = :state")
	Collection<FineDish> findDishesByEpicureIdAndState(int epicureId, State state);
	

}
