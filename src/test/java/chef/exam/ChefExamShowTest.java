package chef.exam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefExamShowTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/exam/exam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String code, final String description,final String helping,
		final String instantationMoment, final String finishingDate, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Quittels");
		super.checkListingExists();
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, helping);
		super.checkColumnHasValue(recordIndex, 2, instantationMoment);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("helping", helping);
		super.checkInputBoxHasValue("instantationMoment", instantationMoment);
		super.checkInputBoxHasValue("finishingDate", finishingDate);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	
	}
	
	// No hay caso negativo tampoco
	
	@Test
	@Order(20)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/quittel/list");
		super.checkPanicExists();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/quittel/list");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/quittel/list");
		super.checkPanicExists();
		super.signOut();
		
	}

	// Ancillary methods -----------------------------------------------------

}
