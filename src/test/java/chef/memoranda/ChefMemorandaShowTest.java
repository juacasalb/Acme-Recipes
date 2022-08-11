package chef.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefMemorandaShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/memoranda/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefShowMemoranda(final int testIndex, final String automaticSeqNum, final String instantiationMoment, final String report, final String link, final String fineDishCode, final String fineDishEpicureUsername) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List all my memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(testIndex);
				
		super.checkFormExists();
		super.checkInputBoxHasValue("automaticSeqNum", automaticSeqNum);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("fineDishCode", fineDishCode);
		super.checkInputBoxHasValue("fineDishEpicureUsername", fineDishEpicureUsername);
		
		super.signOut();
	}
}
