package authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedEpicureCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/epicure/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreatePositive(final int testIndex, final String organisation, final String assertion, final String link) {
		
		super.signUp("testepicure"+testIndex, "testepicure"+testIndex, "Test", "Epicure", "testing@acme.es");
		super.signIn("testepicure"+testIndex, "testepicure"+testIndex);
		
		super.clickOnMenu("Account", "Become a epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Epicure", "My epicure profile");
		
		super.checkInputBoxHasValue("organisation", organisation);
		super.checkInputBoxHasValue("assertion", assertion);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/epicure/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateNegative(final int testIndex, final String organisation, final String assertion, final String link) {
		
		super.signUp("testepicure"+testIndex+"000", "testepicure"+testIndex+"000", "Test", "Chef", "testing@acme.es");
		super.signIn("testepicure"+testIndex+"000", "testepicure"+testIndex+"000");
		
		super.clickOnMenu("Account", "Become a epicure");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.checkErrorsExist();
		
		super.signOut();

	}
	
}
