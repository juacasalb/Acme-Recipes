package any.useraccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/chef.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void consumerListComponents(final int testIndex, final String name, final String surname, final String email, final String username) {
		
		super.clickOnMenu("Users", "List all chefs");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, name);
		super.checkColumnHasValue(testIndex, 1, surname);
		super.checkColumnHasValue(testIndex, 2, email);
		
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("username", username);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/epicure.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void inventorListComponents(final int testIndex, final String name, final String surname, final String email, final String username) {
		
		super.clickOnMenu("Users", "List all epicures");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, name);
		super.checkColumnHasValue(testIndex, 1, surname);
		super.checkColumnHasValue(testIndex, 2, email);
		
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("username", username);
		
	}
}
