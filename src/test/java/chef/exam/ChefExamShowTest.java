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
	public void positiveTest(final int recordIndex, final String theme, final String keylet, final String statement,final String allotment,
		final String instantationMoment, final String finishingDate, final String moreInfo) {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, theme);
		super.checkColumnHasValue(recordIndex, 1, allotment);
		super.checkColumnHasValue(recordIndex, 2, instantationMoment);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("keylet", keylet);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("allotment", allotment);
		super.checkInputBoxHasValue("instantationMoment", instantationMoment);
		super.checkInputBoxHasValue("finishingDate", finishingDate);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
	
	}
	
	// No hay caso negativo tampoco
	
	@Test
	@Order(20)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/ketema/list");
		super.checkPanicExists();
		
		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/ketema/list");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/ketema/list");
		super.checkPanicExists();
		super.signOut();
		
	}

	// Ancillary methods -----------------------------------------------------

}
