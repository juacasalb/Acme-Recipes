package chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CheftemCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/items.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefCreateItemPositive(final int testIndex, final String name, final String unit, final String type,
		final String code, final String description,
		final String retailPrice, final String link) {
		
		super.signIn("chef5", "chef5");
		
		super.clickOnMenu("Chef", "My Ingredients");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("unit", unit);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		if(type.equals("INGREDIENT")) {
			super.clickOnMenu("Chef", "My Ingredients");
			super.checkListingExists();
			super.sortListing(0, "asc");
			super.clickOnListingRecord(testIndex);
		}
		else {
			super.clickOnMenu("Chef", "My Kitchen Utensils");
			super.checkListingExists();
			super.sortListing(0, "asc");
			super.clickOnListingRecord(testIndex-1);
		}
				
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("unit", unit);
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/chef/item/negativeItem.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void chefCreateItemNegative(final int testIndex, final String name, final String unit, final String type,
		final String code, final String description,
		final String retailPrice, final String link) {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My Ingredients");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("unit", unit);
		super.fillInputBoxIn("type", type);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
		
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.checkNotLinkExists("Account");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("provider1", "provider1");
		super.navigate("/chef/item/create");
		super.checkPanicExists();
		super.signOut();
		
	}

}