package chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/deleteP.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamDeleteTestPositive(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd) {
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
		super.checkColumnHasValue(testIndex, 0, title);
		
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkSubmitExists("Delete Delor");
		super.clickOnSubmit("Delete Delor");
		
		super.checkNotErrorsExist();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/deleteN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamDeleteTestNegative(final int testIndex) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of delors associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkNotSubmitExists("Delete Delor");
		
		super.signOut();
	}
}
