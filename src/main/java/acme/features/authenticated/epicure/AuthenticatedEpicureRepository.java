package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface AuthenticatedEpicureRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("SELECT e FROM Epicure e WHERE e.id = :id")
	Epicure findEpicureByEpicureId(int id);

	@Query("SELECT e FROM Epicure e WHERE e.userAccount.id = :accountId")
	Epicure findEpicureByAccountId(int accountId);

}
