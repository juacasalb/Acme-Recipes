package acme.features.chef.memoranda;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.entities.fineDish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefMemorandaCreateService implements AbstractCreateService<Chef, Memorandum> {
	
	@Autowired
	protected ChefMemorandaRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		String fineDishCode, seqNum;
		FineDish fd;
		Long memAmount;
		
		fineDishCode = request.getModel().getString("fineDishCode");
		fd = this.repository.getFineDishByCode(fineDishCode);
		
		memAmount = this.repository.countMemorandum();
		seqNum = this.getSeqNumFromAmountAndFineDishCode(fineDishCode, memAmount);
		
		entity.setFineDish(fd);
		entity.setAutomaticSeqNum(seqNum);
		
		request.bind(entity, errors, "instantiationMoment", "report", "link");		
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"instantiationMoment", "report", "link");
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;
		Memorandum result;
		
		result = new Memorandum();
		
		result.setInstantiationMoment(Date.valueOf(LocalDate.now()));
		return result;
	}

	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String code;
		final FineDish fd;
		
		//Comprueba que se ha marcado la casilla de confirmacion
		if(!errors.hasErrors("confirmation")) {
			errors.state(request, request.getModel().getBoolean("confirmation"), "confirmation", "chef.memoranda.confirmation.required");
		}
		//Comprueba que hay un FineDish con el codigo especificado
		if(!errors.hasErrors("fineDishCode")) {
			code = request.getModel().getString("fineDishCode");
			fd = this.repository.getFineDishByCode(code);
			errors.state(request, fd != null, "fineDishCode", "chef.memoranda.fine-dish-code.fine-dish-doesnt-exist");
		}
		
		
	}

	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
	
	
	private String getSeqNumFromAmountAndFineDishCode(final String code, final Long amount) {
		String aux, result;
		aux = Long.toString(amount+1);
		
		if (amount>9998) {
			result = aux.substring(aux.length()-4);
		}
		else if (amount<999){
			result = String.join("",Collections.nCopies((4-aux.length()),"0")) + aux;
		}
		else {
			result = aux;
		}
		
		return code + ":" + result;
	}
	
	
	
	
}
