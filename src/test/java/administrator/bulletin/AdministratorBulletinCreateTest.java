package administrator.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorBulletinCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/createbulletin-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createBulletinsPositive(final int testIndex, final String heading, final String text, final String flag, final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create Bulletin");
		
		
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("flag", flag);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Authenticated", "Bulletins");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex+1, 1, heading);
		super.checkColumnHasValue(testIndex+1, 2, flag);
		
		super.clickOnListingRecord(testIndex+1);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/bulletin/createbulletin-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createBulletinsNegative(final int testIndex, final String heading, final String text, final String flag, final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Create Bulletin");
		
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("flag", flag);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}