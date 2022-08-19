package acme.features.any.useraccount;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{

	@Query("select a from UserAccount a where a.enabled=true")
	Collection<UserAccount> findEnabledAccounts(); 
	
	@Query("select a from UserAccount a where a.id=:id")
	UserAccount findUserAccountById(int id);
	
}