package chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefAcceptDenyTest extends TestHarness {

	final static private String PSSWRD = "ajidasu2889e2uJJJNS";
	
	@Test
	@Order(10)
	public void initilise() {
		super.signUp("chef47",ChefAcceptDenyTest.PSSWRD,"C47" ,"Doctor" ,"your@ss.gone");
		super.signIn("chef47",ChefAcceptDenyTest.PSSWRD );
		super.clickOnMenu("Account", "Become a chef");
		super.fillInputBoxIn("organisation", "SKKK");
		super.fillInputBoxIn("assertion", "Assert");
		super.clickOnSubmit("Register");
		super.signOut();
		
		super.signUp("epicure47",ChefAcceptDenyTest.PSSWRD,"E47" ,"Doctor" ,"mine@ss.gone");
		super.signIn("epicure47",ChefAcceptDenyTest.PSSWRD );
		super.clickOnMenu("Account", "Become a epicure");
		super.fillInputBoxIn("organisation", "SKKK");
		super.fillInputBoxIn("assertion", "Assert");
		super.clickOnSubmit("Register");
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/action.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void test(final int recordIndex, final String code, final String budget, final String request,
		final String moreInfo, final String username) {
		super.signIn("epicure47", ChefAcceptDenyTest.PSSWRD);
		super.clickOnMenu("Epicure", "My fine dishes");
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("chef.userAccount.username", username);
		super.clickOnSubmit("Create");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");
		
		super.signOut();
		
		super.signIn("chef47", ChefAcceptDenyTest.PSSWRD);
		super.clickOnMenu("Chef", "My proposed dishes");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Deny");
		
		super.signOut();
		
		super.signIn("epicure47", ChefAcceptDenyTest.PSSWRD);
		super.clickOnMenu("Epicure", "My fine dishes");
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("request", "nothing");
		super.clickOnSubmit("Update");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");
		
		super.signOut();
		super.signIn("chef47", ChefAcceptDenyTest.PSSWRD);
		super.clickOnMenu("Chef", "My proposed dishes");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Accept");
		super.checkListingExists();
		
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/chef/fine-dish/list");
		super.checkPanicExists();
		super.signIn("epicure47", ChefAcceptDenyTest.PSSWRD);
		super.navigate("/chef/fine-dish/list");
		super.checkPanicExists();
		super.signOut();
	}
}
