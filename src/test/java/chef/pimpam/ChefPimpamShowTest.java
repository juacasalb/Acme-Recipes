package chef.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefPimpamShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/pimpam/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefPimpamShowTest(final int testIndex, final String pimpamCode, final String itemCode,final String title, final String description, final String link, final String budget, final String instantiationMoment, final String periodStart, final String periodEnd) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List of pimpamps associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		
		super.checkFormExists();
		super.checkInputBoxHasValue("code", pimpamCode);
		super.checkInputBoxHasValue("itemCode", itemCode);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("periodStart", periodStart);
		super.checkInputBoxHasValue("periodEnd", periodEnd);
		
		super.signOut();
	}

}
