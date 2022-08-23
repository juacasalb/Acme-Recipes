package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefRecipePublishTest extends TestHarness{

	@Test
	@Order(10)
	public void chefPublishRecipePositive() {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

	}
	
	@Test
	@Order(20)
	public void chefPublishRecipeNegative() {
		
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.checkNotButtonExists("Publish");

	}

	
}
