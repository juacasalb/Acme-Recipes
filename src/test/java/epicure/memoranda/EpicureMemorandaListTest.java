package epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureListMemoranda(final int testIndex, final String automaticSeqNum, final String instantiationMoment, final String report, final String link, final String fineDishCode, final String fineDishEpicureUsername) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, automaticSeqNum);
		super.checkColumnHasValue(testIndex, 1, instantiationMoment);
		super.checkColumnHasValue(testIndex, 2, fineDishCode);
		super.checkColumnHasValue(testIndex, 3, fineDishEpicureUsername);
		
		super.signOut();
	}
}
