package acme.features.chef.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memorandum;
import acme.entities.fineDish.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandaRepository extends AbstractRepository {
	@Query("select m from Memorandum m where m.fineDish.chef.id = :id")
	Collection<Memorandum> findByChefId(int id);
	
	@Query("select m from Memorandum m where m.id =:id")
	Memorandum getMemorandumById(int id);
	
	@Query("select fd from FineDish fd where fd.code =:code")
	FineDish getFineDishByCode(String code);
	
	@Query("select count(m) from Memorandum m")
	Long countMemorandum();
}
