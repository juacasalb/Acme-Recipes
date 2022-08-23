package chef.recipe;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class ChefRecipeDeleteTest extends TestHarness{

	@Test
	@Order(10)
	public void positiveDeleteRecipe() {
	
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
				
	}
	
	@Test
	@Order(20)
	public void negativeDeleteItem() {
	
		super.signIn("chef2", "chef2");
		
		super.clickOnMenu("Chef", "My recipes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
				
	}
	
}
