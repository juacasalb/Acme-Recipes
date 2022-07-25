package epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/memoranda.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureListMemoranda(final int testIndex, final String automaticSeqNum, final String instantiationMomentList, final String instantiationMomentShow, final String report, final String link, final String fineDishCode, final String fineDishEpicureUsername) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, automaticSeqNum);
		super.checkColumnHasValue(testIndex, 1, instantiationMomentList);
		super.checkColumnHasValue(testIndex, 2, fineDishCode);
		super.checkColumnHasValue(testIndex, 3, fineDishEpicureUsername);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/memoranda.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureShowMemoranda(final int testIndex, final String automaticSeqNum, final String instantiationMomentList, final String instantiationMomentShow, final String report, final String link, final String fineDishCode, final String fineDishChefUsername) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(testIndex);
				
		super.checkFormExists();
		super.checkInputBoxHasValue("automaticSeqNum", automaticSeqNum);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMomentShow);
		super.checkInputBoxHasValue("fineDishCode", fineDishCode);
		super.checkInputBoxHasValue("fineDishChefUsername", fineDishChefUsername);
		
	}
	
}
