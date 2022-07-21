package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Quantity;
import acme.entities.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select r from Recipe r where r.published = true")
	Collection<Recipe> findManyRecipesPublished();
	
	@Query("select cc.defaultCurrency from CurrencyConfiguration cc")
	String findSystemCurrency();
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findQuantityByRecipeId(int id);

}

