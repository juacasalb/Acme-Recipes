package chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamCreateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/createP.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamCreateTestPositive(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of delors associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Delor");
		super.checkFormExists();
		super.fillInputBoxIn("itemCode", itemCode);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.clickOnSubmit("Create Delor");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("itemCode", itemCode);
		super.checkInputBoxHasValue("subject", title);
		super.checkInputBoxHasValue("explanation", description);
		super.checkInputBoxHasValue("moreInfo", link);
		super.checkInputBoxHasValue("income", budget);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		
		super.checkSubmitExists("Delete Delor");
		super.clickOnSubmit("Delete Delor");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/createN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamCreateTestNegative(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd, final String errorField) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of delors associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Delor");
		super.checkFormExists();
		super.fillInputBoxIn("itemCode", itemCode);
		super.fillInputBoxIn("subject", title);
		super.fillInputBoxIn("explanation", description);
		super.fillInputBoxIn("moreInfo", link);
		super.fillInputBoxIn("income", budget);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.clickOnSubmit("Create Delor");
		
		super.checkErrorsExist(errorField);
		
		super.signOut();
	}
	
}
