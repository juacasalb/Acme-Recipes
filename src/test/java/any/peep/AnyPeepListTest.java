package any.peep;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPeepListTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/any/peep/peep.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void anonymousListPeep(final int testIndex, final String instantationMoment, final String heading, final String writer, final String pieceOfText, final String email) {
		
		super.clickOnMenu("Peeps", "List of Peeps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(testIndex, 0, heading);
		super.checkColumnHasValue(testIndex, 1, pieceOfText);
		super.checkColumnHasValue(testIndex, 2, instantationMoment);
		super.checkColumnHasValue(testIndex, 3, writer);
		super.checkColumnHasValue(testIndex, 4, email);
	}
}