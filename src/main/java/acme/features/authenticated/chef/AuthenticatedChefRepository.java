package acme.features.authenticated.chef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface AuthenticatedChefRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("SELECT c FROM Chef c WHERE c.id = :id")
	Chef findChefByChefId(int id);

	@Query("SELECT c FROM Chef c WHERE c.userAccount.id = :accountId")
	Chef findChefByAccountId(int accountId);

}
