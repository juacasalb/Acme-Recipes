package chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefItemPublishTest extends TestHarness{

	@Test
	@Order(10)
	public void positivePublishItem() {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkInputBoxHasValue("published", "true");
		
		super.signOut();
		
	}
	
	@Test
	@Order(20)
	public void positivePublishNegativeItem() {
		
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		
		super.signOut();
		
	}
	
}
