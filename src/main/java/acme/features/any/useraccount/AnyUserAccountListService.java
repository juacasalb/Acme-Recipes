package acme.features.any.useraccount;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount>{

	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Collection<UserAccount> findMany(final Request<UserAccount> request){
		
		final String roleString = request.getModel().getString("role");
		
		final Collection<UserAccount> userAccounts = this.repository.findEnabledAccounts();
		
		final List<UserAccount> result = new ArrayList<>();
		
		for(final UserAccount account: userAccounts) {
			if(!account.isAnonymous() && !account.hasRole(Administrator.class)) {
				for(final UserRole u: account.getRoles()) {
					if(u.getAuthorityName().equalsIgnoreCase(roleString)) {
						result.add(account);
					}
				}
			}
		}
		
		return result;
		
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "identity.name", "identity.surname", "identity.email");
		
	}
	
}