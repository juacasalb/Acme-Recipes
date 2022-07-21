package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
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
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;
	
	@Valid
	@Transient
	protected Money retailPrice;
	
}
