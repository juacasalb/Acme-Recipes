package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.fineDish.FineDish;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Memorandum extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	//Automatic sequence number
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}:[0-9]{4}$")
	@NotBlank
	protected String 						automaticSeqNum;
	
	//Instantiation moment
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date 							instantiationMoment;
	
	//Report
	@Length(max =  255)
	@NotBlank
	protected String 						report;
	
	//Optional link
	@URL
	protected String 						link;
	
	
	//Fine dish
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected FineDish 						fineDish;
}
