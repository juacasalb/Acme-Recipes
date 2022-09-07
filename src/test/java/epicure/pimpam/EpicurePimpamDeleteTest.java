package epicure.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicurePimpamDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/pimpam/deleteP.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicurePimpamDeleteTestPositive(final int testIndex, final String itemCode, final String title, final String description, final String link, final String budget, final String periodStart, final String periodEnd) {
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List of pimpamps associated with my items");
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
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(testIndex, 0, title);
		
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkSubmitExists("Delete Pimpam");
		super.clickOnSubmit("Delete Pimpam");
		
		super.checkNotErrorsExist();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/pimpam/deleteN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicurePimpamDeleteTestNegative(final int testIndex) {
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List of pimpamps associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkNotSubmitExists("Delete Pimpam");
		
		super.signOut();
	}
}
