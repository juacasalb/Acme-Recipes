package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeListTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, final String description) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, heading);
		super.checkColumnHasValue(recordIndex, 2, description);
		
		super.signOut();
		
	}
	
	//No hay test negativo, solo se listan las recetas que existen
	
	@Test
	@Order(20)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/recipe/list");
		super.checkPanicExists();
		super.signOut();
		
	}

	// Ancillary methods ------------------------------------------------------

}
