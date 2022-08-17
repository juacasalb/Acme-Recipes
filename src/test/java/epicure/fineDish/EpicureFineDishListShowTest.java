package epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishListShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/fine-dish.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void epicureListAndShowFineDish(final int recorIndex, final String code, final String budget, final String startPeriod, 
		final String endPeriod, final String request, final String moreInfo,final String chName, final String chSurname, final String chEmail,
		final String state) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My fine dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recorIndex, 0, code);
		super.checkColumnHasValue(recorIndex, 1, budget);
		super.checkColumnHasValue(recorIndex, 2, startPeriod);
		super.checkColumnHasValue(recorIndex, 3, endPeriod);
		
		super.clickOnListingRecord(recorIndex);
		
		super.checkInputBoxHasValue("state", state);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("request", request);
		//super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("chef.identity.name", chName);
		super.checkInputBoxHasValue("chef.identity.surname", chSurname);
		super.checkInputBoxHasValue("chef.identity.email", chEmail);
		
	}
}
