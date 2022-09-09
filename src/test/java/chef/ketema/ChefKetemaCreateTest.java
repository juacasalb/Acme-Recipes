package chef.ketema;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKetemaCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/ketema/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateRecipePositive(final int testIndex, final String theme, final String statement,
		final String allotment, final String finishingDate, final String moreInfo) {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("allotment", allotment);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Create");
						
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("allotment", allotment);
		super.checkInputBoxHasValue("finishingDate", finishingDate);
		super.checkInputBoxHasValue("moreInfo", moreInfo);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/ketema/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateRecipeNegative(final int testIndex, final String theme, final String statement,
		final String allotment, final String finishingDate, final String moreInfo) {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("allotment", allotment);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.clickOnSubmit("Create");
						
		super.checkErrorsExist();

	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/ketema/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/ketema/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/ketema/create");
		super.checkPanicExists();
		super.signOut();
	
	}

}
