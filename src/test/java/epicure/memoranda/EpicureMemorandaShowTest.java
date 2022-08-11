package epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaShowTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureShowMemoranda(final int testIndex, final String automaticSeqNum, final String instantiationMoment, final String report, final String link, final String fineDishCode, final String fineDishChefUsername) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("automaticSeqNum", automaticSeqNum);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("fineDishCode", fineDishCode);
		super.checkInputBoxHasValue("fineDishChefUsername", fineDishChefUsername);
		
		super.signOut();
	}
	
}
