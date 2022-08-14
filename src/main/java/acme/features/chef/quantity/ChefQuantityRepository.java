package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefQuantityRepository extends AbstractRepository{
	
	@Query("select i from Quantity i where i.id = :quantityId")
	Quantity findQuantityById(int quantityId);
	
	@Query("SELECT a FROM Item a WHERE a.id = :id")
	Item findItemById(int id);
	
	@Query("select r from Recipe r where r.id = :id")
	Recipe findRecipeById(int id);
	
	@Query("select i from Item i where i.published = true and i.type = :type and i not in (select q.item from Quantity q where q.recipe.id = :recipeId)")
	Collection<Item> findManyItemsNotInRecipe(int recipeId, ItemType type);
	
	@Query("select i from Quantity i where i.recipe.id = :recipeId and i.item.type=:type")
	Collection<Quantity> findManyQuantitiesByRecipeIdAndType(int recipeId, ItemType type);

}
