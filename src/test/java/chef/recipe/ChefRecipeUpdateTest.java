package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefUpdateRecipePositive(final int testIndex, final String code, final String heading,
		final String description, final String preparationNotes, final String link) {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
						
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefUpdateRecipeNegative(final int testIndex, final String code, final String heading,
		final String description, final String preparationNotes, final String link) {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
						
		super.checkErrorsExist();

	}
	
	@Test
	@Order(30)
	public void chefUpdateNegativeTest() {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkNotSubmitExists("Update");
		
	}
	
}
