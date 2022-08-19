package acme.forms;

import java.io.Serializable;
import java.util.List;

import acme.system.configuration.CurrencyConfiguration;
import acme.system.configuration.SpamTuple;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SystemConfiguration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CurrencyConfiguration currencyConfiguration;
	List<SpamTuple> spamConfiguration;

}
