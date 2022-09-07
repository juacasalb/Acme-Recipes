package authenticated.epicure;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedEpicureUpdateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/epicure/update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateTest(final int recordIndex, final String organisation, final String assertion, final String link) {
		super.signIn("epicure1", "epicure1");
		super.clickOnMenu("Epicure", "My epicure profile");
		super.checkFormExists();
		super.fillInputBoxIn("organisation", organisation);
		super.fillInputBoxIn("assertion", assertion);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		super.checkNotErrorsExist();
	}
}
