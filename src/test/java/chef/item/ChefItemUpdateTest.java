package chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefItemUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/updateItem.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateItemPositive(final int testIndex, final String name, final String unit, final String type,
		final String code, final String description,
		final String retailPrice, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("unit", unit);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("unit", unit);
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);		
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/updateNegativeItem.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateItemNegative(final int testIndex, final String name, final String unit, final String type,
		final String code, final String description,
		final String retailPrice, final String link) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(testIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("unit", unit);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
}