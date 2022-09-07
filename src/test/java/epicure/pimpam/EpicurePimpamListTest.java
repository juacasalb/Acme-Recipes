package epicure.pimpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class EpicurePimpamListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/pimpam/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicurePimpamListTest(final int testIndex, final String title, final String pimpamCode, final String budget, final String itemCode, final String instantiationMoment) {
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List of pimpamps associated with my items");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, title);
		super.checkColumnHasValue(testIndex, 1, pimpamCode);
		super.checkColumnHasValue(testIndex, 2, budget);
		super.checkColumnHasValue(testIndex, 3, itemCode);
		super.checkColumnHasValue(testIndex, 4, instantiationMoment);
		
		super.signOut();
	}
	
	
}
