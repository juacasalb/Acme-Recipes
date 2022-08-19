package administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/data.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void chefListAndShowFineDish(final int recordIndex) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator","System configuration");
		super.checkFormExists();
		
		super.clickOnButton("Return");
		
		super.clickOnMenu("Authenticated", "System Configuration");
		super.checkFormExists();
	}
}
