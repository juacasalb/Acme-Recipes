package chef.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefFineDishListShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/fine-dish/fine-dish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefListAndShowFineDish(final int recorIndex, final String code, final String helping, final String startPeriod, 
		final String endPeriod, final String request, final String moreInfo,final String epName, final String epSurname, final String epEmail,
		final String state) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recorIndex, 0, code);
		super.checkColumnHasValue(recorIndex, 1, helping);
		super.checkColumnHasValue(recorIndex, 2, startPeriod);
		super.checkColumnHasValue(recorIndex, 3, endPeriod);
		
		super.clickOnListingRecord(recorIndex);
		
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("epicure.identity.name", epName);
		super.checkInputBoxHasValue("epicure.identity.surname", epSurname);
		super.checkInputBoxHasValue("epicure.identity.email", epEmail);
		
	}
}
