package chef.exam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefExamCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/exam/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateRecipePositive(final int testIndex, final String title, final String description,
		final String budget, final String finishingDate, final String link) {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "List Pimpams");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
						
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("finishingDate", finishingDate);
		super.checkInputBoxHasValue("link", link);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/exam/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateRecipeNegative(final int testIndex, final String title, final String description,
		final String budget, final String finishingDate, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Pimpams");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
						
		super.checkErrorsExist();

	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/pimpam/create");
		super.checkPanicExists();
		super.signOut();
	
	}

}
