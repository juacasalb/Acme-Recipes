package authenticated.bulletin;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedBulletinListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/bulletin.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefListBulletins(final int testIndex, final String instantiationMoment, final String heading, final String text, final String flag, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Authenticated", "Bulletins");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, instantiationMoment);
		super.checkColumnHasValue(testIndex, 1, heading);
		super.checkColumnHasValue(testIndex, 2, flag);
		
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/bulletin/bulletin.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureListBulletins(final int testIndex, final String instantiationMoment, final String heading, final String text, final String flag, final String link) {
		
		super.signIn("epicure1", "epicure1");
		
		super.clickOnMenu("Authenticated", "Bulletins");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, instantiationMoment);
		super.checkColumnHasValue(testIndex, 1, heading);
		super.checkColumnHasValue(testIndex, 2, flag);
		
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		super.checkInputBoxHasValue("heading", heading);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("flag", flag);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}

}