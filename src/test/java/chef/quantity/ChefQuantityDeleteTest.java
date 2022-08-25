package chef.quantity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefQuantityDeleteTest extends TestHarness{
	
	@Test
	@Order(10)
	public void positiveDeleteQuantity() {
	
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		super.checkNotPanicExists();
		
	}
	
	@Test
	@Order(20)
	public void negativeDeleteQuantity() {
		
		super.signIn("chef4", "chef4");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.checkFormExists();
		super.clickOnButton("Items");
		super.checkListingExists();
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
	
	}

}
