package authenticated.chef;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedChefUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/chef/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateTest(final int recordIndex, final String organisation, final String assertion, final String link) {
		super.signIn("chef1", "chef1");
		super.clickOnMenu("Chef", "My chef profile");
		super.checkFormExists();
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
	}
}
