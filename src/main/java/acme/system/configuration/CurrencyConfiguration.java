package acme.system.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CurrencyConfiguration extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	protected String defaultCurrency;
	@NotBlank
	protected String acceptedCurrencies;

	
}
