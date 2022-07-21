package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 101)
	protected String heading;
	
	@NotBlank
	@Length(min = 1, max = 256)
	protected String description;
	
	@NotBlank
	@Length(min = 1, max = 256)
	protected String preparationNotes;
	
	@URL
	protected String link;
	
	protected boolean published;
	
}
