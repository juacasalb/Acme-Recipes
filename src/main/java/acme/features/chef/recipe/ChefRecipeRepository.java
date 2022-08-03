package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefRecipeRepository extends AbstractRepository{
	
	@Query("SELECT r FROM Recipe r WHERE r.chef.id = :id")
	Collection<Recipe> findManyRecipesByChefId(int id);
	
	@Query("SELECT r FROM Recipe r WHERE r.id = :id")
	Recipe findOneRecipeById(int id);
	
	@Query("select cc.defaultCurrency from CurrencyConfiguration cc")
	String findSystemCurrency();
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findQuantityByRecipeId(int id);
	
	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);

}
