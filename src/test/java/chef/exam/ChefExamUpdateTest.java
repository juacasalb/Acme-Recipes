package chef.exam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefExamUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/exam/examupdatepositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title,  final String description,final String helping, final String link, final String finishingDate) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Quittels");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Chef", "List Quittels");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("helping", helping);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("finishingDate", finishingDate);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/exam/examupdatenegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title,  final String description,final String helping, final String link, final String finishingDate) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Quittels");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
	
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("helping", helping);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
	
		super.signOut();
	}

	
	@Test
	@Order(30)
	public void hackingTest() {
		//El framework no permite actualmente realizar un hackingTest. Manualmente sería:
			//Iniciar sesión con otro chef (4 por ejemplo)
			//Acceder a la url del item con su id
	}

}