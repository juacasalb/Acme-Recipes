package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeShowTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/recipe.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String heading, final String description,final String preparationNotes,
		final String link, final String published, final String chef) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		super.checkInputBoxHasValue("chef.userAccount.username", chef);
		
		super.signOut();
	
	}
	
	// No hay caso negativo tampoco
	
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

	// Ancillary methods -----------------------------------------------------

}
