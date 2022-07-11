package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chef extends UserRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 1, max = 101)
	protected String organisation;

	@NotBlank
	@Length(min = 1, max = 256)
	protected String assertion;
	
	@URL
	protected String link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
