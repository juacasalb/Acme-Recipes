package any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepCreateTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/peepCreate.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String heading, final String writer, final String pieceOfText, final String email) {
		super.clickOnMenu("Peeps", "List of Peeps");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		
		super.fillInputBoxIn("heading", heading);
		super.fillInputBoxIn("writer", writer);
		super.fillInputBoxIn("pieceOfText", pieceOfText);
		super.fillInputBoxIn("email", email);
		super.clickOnSubmit("Create");
		super.checkErrorsExist();
	}
}
