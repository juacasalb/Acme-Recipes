package authenticated.chef;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedChefCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/chef/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreatePositive(final int testIndex, final String organisation, final String assertion, final String link) {
		
		super.signUp("testchef"+testIndex, "testchef"+testIndex, "Test", "Chef", "testing@acme.es");
		super.signIn("testchef"+testIndex, "testchef"+testIndex);
		
		super.clickOnMenu("Account", "Become a chef");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Chef", "My chef profile");
		
		super.checkInputBoxHasValue("organisation", organisation);
		super.checkInputBoxHasValue("assertion", assertion);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/chef/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateNegative(final int testIndex, final String organisation, final String assertion, final String link) {
		
		super.signUp("testchef"+testIndex+"000", "testchef"+testIndex+"000", "Test", "Chef", "testing@acme.es");
		super.signIn("testchef"+testIndex+"000", "testchef"+testIndex+"000");
		
		super.clickOnMenu("Account", "Become a chef");
		
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Register");
		
		super.checkErrorsExist();
		
		super.signOut();

	}
	
}
