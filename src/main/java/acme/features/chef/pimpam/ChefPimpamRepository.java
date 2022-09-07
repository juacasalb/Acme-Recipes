package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.framework.repositories.AbstractRepository;
import acme.system.configuration.CurrencyConfiguration;


@Repository
public interface ChefPimpamRepository extends AbstractRepository{
		
	@Query("select p from Pimpam p where p.item.chef.id =:id")
	Collection<Pimpam> getPimpamOfUserId(int id);
	
	@Query("select p from Pimpam p where p.id =:id")
	Pimpam getPimpamById(int id);
		
	@Query("select it from Item it where it.code =:code")
	Item getItemFromCode(String code);
	
	@Query("select cc from CurrencyConfiguration cc")
	CurrencyConfiguration getCurrencyConfiguration();
}
