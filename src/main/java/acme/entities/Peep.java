package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Peep extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantationMoment;
	
	@Length(min = 1, max = 101)
	@NotBlank
	protected String heading;
	
	@Length(min = 1, max = 101)
	@NotBlank
	protected String writer;
	
	@Length(min = 1, max = 256)
	@NotBlank
	protected String pieceOfText;
	
	@Email
	protected String email; 
	

}
