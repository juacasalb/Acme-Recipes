package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SystemConfiguration extends AbstractEntity{

	protected static final long	serialVersionUID	= 1L;
		
	@NotBlank
	private String systemCurrency;
	
	@NotBlank
	private String acceptedCurrencies;
	
	@NotBlank
	private String strongSpam;
	
	@Min(0)
	@Max(1)
	private double strongThreshold;
	
	@NotBlank
	private String weakSpam;
	
	@Min(0)
	@Max(1)
	private double weakThreshold;
		
	
}