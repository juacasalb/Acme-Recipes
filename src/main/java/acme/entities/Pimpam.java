package acme.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
public class Pimpam extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp="^[0-9]{6}:[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$")
//	@Pattern(regexp="[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
	/* yy-mm-dd (fecha de instantiationMoment)
	 * yy: [0-9]{2} (caracter entre 0 y 9 inclusive, 2 veces)
	 * mm: (0[1-9]|1[0-2])
	 * 		opcion 1:
	 * 			0[1-9] (caracter 0 seguido de un caracter entre 1 y 9 inclusive)
	 * 		opcion 2:
	 * 			1[0-2] (caracter 1 seguido de un caracter entre 0 y 2 inclusive)
	 * dd: (0[1-9]|[1-2][0-9]|3[0-1])
	 * 		opcion 1: 
	 * 			0[1-9] (caracter 0 seguido de un caracter entre 1 y 9 inclusive)
	 * 		opcion 2:
	 * 			[1-2][0-9] (caracter entre 1 y 2 inclusive seguido de un caracter entre 0 y 9 inclusive)
	 * 		opcion 3:
	 * 			3[0-1] (caracter 3 seguido de caracter entre 0 y 1 inclusive)
	 * */
	protected String code;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date instantiationMoment;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String description;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@URL
	protected String link;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodStart;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodEnd;
	
	@Valid
	@OneToOne(optional = false)
	protected Item item;
}
