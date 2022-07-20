package acme.system.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamTuple extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	protected String spamWord;
	@Range(min=0,max=1)
	protected double threshold;

}