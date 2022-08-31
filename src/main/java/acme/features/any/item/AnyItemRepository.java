package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{

	@Query("SELECT i FROM Item i WHERE i.type = 1 AND published=true")
	Collection<Item> findAllKitchenUtensils();
	
	@Query("SELECT i FROM Item i WHERE i.id=:id")
	Item findItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.type = 0 AND published=true")
	Collection<Item> findAllIngredients();
	
	
}
