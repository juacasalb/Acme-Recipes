package chef.ketema;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefKetemaDeleteTest extends TestHarness{
	
	@Test
	@Order(10)
	public void positiveDelete() {
	
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Ketemas");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
				
	}

}