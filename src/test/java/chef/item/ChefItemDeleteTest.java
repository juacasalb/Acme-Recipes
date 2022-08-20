package chef.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefItemDeleteTest extends TestHarness{

	@Test
	@Order(10)
	public void positiveDeleteItem() {
	
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My Kitchen Utensils");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
				
	}
	
	@Test
	@Order(20)
	public void negativeDeleteItem() {
	
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "My Ingredients");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
				
	}
		
}