package chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamUpdateTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/updateP.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamUpdateTestPositive(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd, final String updateTitle, final String updateDescription, final String updateLink, final String updateBudget, final String updatePeriodStart, final String updatePeriodEnd) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of pimpamps associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Pimpam");
		super.checkFormExists();
		super.fillInputBoxIn("itemCode", itemCode);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.clickOnSubmit("Create Pimpam");
	
		super.checkNotErrorsExist();
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkSubmitExists("Update Pimpam");
		
		super.fillInputBoxIn("title", updateTitle);
		super.fillInputBoxIn("description", updateDescription);
		super.fillInputBoxIn("budget", updateBudget);
		super.fillInputBoxIn("link", updateLink);
		super.fillInputBoxIn("periodStart", updatePeriodStart);
		super.fillInputBoxIn("periodEnd", updatePeriodEnd);
		super.clickOnSubmit("Update Pimpam");
		
		super.checkNotErrorsExist();
	
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("itemCode", itemCode);
		super.checkInputBoxHasValue("title", updateTitle);
		super.checkInputBoxHasValue("description", updateDescription);
		super.checkInputBoxHasValue("link", updateLink);
		super.checkInputBoxHasValue("budget", updateBudget);
		super.checkInputBoxHasValue("periodStart", updatePeriodStart);
		super.checkInputBoxHasValue("periodEnd", updatePeriodEnd);
		
		super.checkSubmitExists("Delete Pimpam");
		super.clickOnSubmit("Delete Pimpam");
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/updateN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamUpdateTestNegative(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd, final String updateBudget, final String updatePeriodStart, final String updatePeriodEnd, final String errorField) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of pimpamps associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnButton("Create Pimpam");
		super.checkFormExists();
		super.fillInputBoxIn("itemCode", itemCode);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("periodStart", periodStart);
		super.fillInputBoxIn("periodEnd", periodEnd);
		super.clickOnSubmit("Create Pimpam");
		
		super.checkNotErrorsExist();
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkSubmitExists("Update Pimpam");
		
		super.fillInputBoxIn("budget", updateBudget);
		super.fillInputBoxIn("periodStart", updatePeriodStart);
		super.fillInputBoxIn("periodEnd", updatePeriodEnd);
		super.clickOnSubmit("Update Pimpam");
		
		super.checkErrorsExist(errorField);
		
		super.checkSubmitExists("Delete Pimpam");
		super.clickOnSubmit("Delete Pimpam");
		
		super.signOut();
	}
}
