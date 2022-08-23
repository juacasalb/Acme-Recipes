package chef.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefQuantityShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/quantity/showquantity.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String number, final String type,
		final String name, final String itemCode, final String retailPrice,
		final String recipeCode) {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(0);
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("number", number);
		super.checkInputBoxHasValue("item.type", type);
		super.checkInputBoxHasValue("item.name", name);
		super.checkInputBoxHasValue("item.code", itemCode);
		super.checkInputBoxHasValue("item.retailPrice", retailPrice);
		super.checkInputBoxHasValue("recipe.code", recipeCode);

		super.signOut();
		
	}

}
