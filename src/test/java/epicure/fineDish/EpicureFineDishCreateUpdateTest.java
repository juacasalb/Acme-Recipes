package epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishCreateUpdateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createTestPositive(final int recordIndex, final String code, final String budget, final String request,
		final String moreInfo, final String username) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.clickOnButton("Create");
		

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("chef.userAccount.username", username);
		
		super.clickOnSubmit("Create");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createTestNegative(final int recordIndex, final String code, final String budget, final String request,
		final String moreInfo, final String username, final String sDate, final String eDate) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.clickOnButton("Create");
		

		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		if(sDate.length()!=0||eDate.length()!=0) {
			super.fillInputBoxIn("startPeriod", sDate);
			super.fillInputBoxIn("endPeriod", eDate);
		}
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("chef.userAccount.username", username);
		
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/updateN.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void updateTestPositive(final int recordIndex,  final String code, final String budget, final String request,
		final String moreInfo, final String username, final String sDate, final String eDate) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("request", request);
		super.fillInputBoxIn("budget", budget);
		if(sDate.length()!=0||eDate.length()!=0) {
			super.fillInputBoxIn("startPeriod", sDate);
			super.fillInputBoxIn("endPeriod", eDate);
		}
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("chef.userAccount.username", username);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/updateP.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void updateTestNegative(final int recordIndex, final String request) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(3, "asc");
		
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("request", request);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		
		super.checkInputBoxHasValue("request", request);
		super.signOut();
	}
	
	@Test
	@Order(50)
	public void hackingTest() {
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signIn("chef1", "chef1");
		super.navigate("/epicure/fine-dish/create");
		super.checkPanicExists();
		super.signOut();
	}
}
