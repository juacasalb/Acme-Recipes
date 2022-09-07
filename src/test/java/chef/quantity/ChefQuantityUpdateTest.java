package chef.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefQuantityUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefUpdateQuantityPositive(final int recordIndex, final String number, final String type,
		final String name, final String itemCode, final String retailPrice,
		final String recipeCode, final String itemId) {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		
		super.clickOnListingRecord(1);		
		super.fillInputBoxIn("number", number);
		super.clickOnSubmit("Update");
						
		super.checkListingExists();
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("number", number);
		super.checkInputBoxHasValue("item.type", type);
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.code", itemCode);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("recipe.code", recipeCode);

	}
	
	@Test
	@Order(20)
	public void chefUpdateQuantityNegative() {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		
		super.clickOnListingRecord(0);
		super.fillInputBoxIn("number", "5");
		super.clickOnSubmit("Update");
						
		super.checkErrorsExist();

	}
	
	@Test
	@Order(30)
	public void chefUpdateQuantityNegative2() {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		
		super.clickOnListingRecord(0);
		super.checkNotButtonExists("Update");
		
		super.signOut();
	}
}
