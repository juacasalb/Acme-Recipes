package epicure.memoranda;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureMemorandaCreateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/create-memoranda-pos.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int testIndex, final String automaticSeqNum, final String report, final String link, final String fineDishCode, final String fineDishChefUsername) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.clickOnButton("Create a new memorandum");
		
		super.checkFormExists();
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("fineDishCode", fineDishCode);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create memorandum");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("fineDishChefUsername", fineDishChefUsername);
		super.checkInputBoxHasValue("automaticSeqNum", automaticSeqNum);
		super.checkInputBoxHasValue("report", report);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("fineDishCode", fineDishCode);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/memoranda/create-memoranda-neg.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int testIndex, final String report, final String link, final String fineDishCode) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Epicure", "List all my memorandums");
		super.clickOnButton("Create a new memorandum");
		
		super.checkFormExists();
		super.fillInputBoxIn("report", report);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("fineDishCode", fineDishCode);
		super.fillInputBoxIn("confirmation", "false");
		super.clickOnSubmit("Create memorandum");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	@Test
	@Order(10)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();
		
		super.signIn("chef1", "chef1");
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator", "administrator");
		super.navigate("/epicure/memorandum/create");
		super.checkPanicExists();
		super.signOut();
	}
}
