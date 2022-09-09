package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ketema extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(unique=true)
	//Lo del final es para que si se crean dos Ketema o más en un día, lo diferencie de los otros, ya que el código sesría el mismo
	@Pattern(regexp="^[a-zA-Z0-9]{6}:[0-9]{2}[0-9]{2}:[0-9]{2}(-[0-9]{2})?$")
	protected String keylet;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date instantationMoment;
	
	@NotBlank
	@Length(min = 1, max = 101)
	protected String theme;
	
	@NotBlank
	@Length(min = 1, max = 256)
	protected String statement;
	
	@NotNull
	@Valid
	protected Money allotment;
	
	@URL
	protected String moreInfo;
	
	@NotNull
	@Future
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finishingDate;
	
	@Valid
	@OneToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	protected Item item;
	
	@Valid
	@Temporal(TemporalType.TIMESTAMP)	
	protected Date timeInterval;
	
	@Transient
	protected void getPeriod() {
		this.timeInterval = new Date(this.finishingDate.getTime() - this.instantationMoment.getTime());
	}
	
}
