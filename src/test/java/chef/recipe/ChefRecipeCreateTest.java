package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefRecipeCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateRecipePositive(final int testIndex, final String code, final String heading,
		final String description, final String preparationNotes, final String link) {
		
		super.signIn("chef5", "chef5");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
						
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("preparationNotes", preparationNotes);
		super.checkInputBoxHasValue("link", link);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/recipe/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateRecipeNegative(final int testIndex, final String code, final String heading,
		final String description, final String preparationNotes, final String link) {
		
		super.signIn("chef5", "chef5");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("preparationNotes", preparationNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
						
		super.checkErrorsExist();

	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/recipe/create");
		super.checkPanicExists();
		super.signOut();
	
	}
	
}
