package acme.features.chef.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefItemRepository extends AbstractRepository{

	@Query("SELECT a FROM Item a WHERE a.chef.id = :id AND a.type = 0")
	Collection<Item> findIngredientsByChefId(int id);
	
	@Query("SELECT a FROM Item a WHERE a.chef.id = :id AND a.type = 1")
	Collection<Item> findKitchenUtensilsByChefId(int id);
	
	@Query("SELECT a FROM Item a WHERE a.chef.id = :id")
	Collection<Item> findItemsByChefId(int id);
	
	@Query("SELECT a FROM Item a WHERE a.id = :id")
	Item findItemById(int id);
	
	@Query("SELECT i FROM Chef i WHERE i.id=:id")
	Chef findChefById(int id);
	
	@Query("select i from Item i where i.published = true and i not in (select q.item from Quantity q where q.recipe.id = :recipeId)")
	Collection<Item> findManyPublishedAndValidItems(int recipeId);

}
