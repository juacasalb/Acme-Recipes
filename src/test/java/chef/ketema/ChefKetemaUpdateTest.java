package chef.ketema;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefKetemaUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/ketema/ketemaUpdatepositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String theme,  final String statement,final String allotment,
		final String moreInfo, final String finishingDate) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("allotment", allotment);
		super.fillInputBoxIn("moreInfo", moreInfo);
		super.fillInputBoxIn("finishingDate", finishingDate);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("allotment", allotment);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		super.checkInputBoxHasValue("finishingDate", finishingDate);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/ketema/ketemaUpdatenegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String theme,  final String statement,final String allotment,
		final String moreInfo, final String finishingDate) {
		super.signIn("chef1", "chef1");

		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
	
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("allotment", allotment);
		super.fillInputBoxIn("moreInfo", moreInfo);
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