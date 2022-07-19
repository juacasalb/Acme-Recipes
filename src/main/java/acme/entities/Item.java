package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
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
public class Item extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(min = 1, max = 101)
	protected String name;
	
	@NotNull
	protected ItemType type;
	
	@Length(min = 1, max = 15)
	protected String unit;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 256)
	protected String description;
	
	@NotNull
	@Valid
	protected Money retailPrice;
	
	@URL
	protected String link;
	
	@NotNull
	protected Boolean published;
	
	@NotNull
	@Valid
	@ManyToOne(optional=true)
	protected Recipe recipe;
}
