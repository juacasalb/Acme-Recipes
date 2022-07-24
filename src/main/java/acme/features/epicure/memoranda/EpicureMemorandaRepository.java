package acme.features.epicure.memoranda;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureMemorandaRepository extends AbstractRepository {
	@Query("select m from Memorandum m where m.fineDish.epicure.id = :id")
	Collection<Memorandum> findByEpicureId(int id);
}
