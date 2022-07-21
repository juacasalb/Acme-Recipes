package acme.entities.fineDish;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	protected State state;
	
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique=true)
	@NotBlank
	protected String code;
	@NotBlank
	@Length(max= 256)
	protected String request;
	@NotNull
	protected Money budget;
	@NotNull
	protected Date startPeriod;
	@NotNull
	protected Date endPeriod;
	@URL
	protected String moreInfo;
	
	//Relacion con epicure y chef

}
