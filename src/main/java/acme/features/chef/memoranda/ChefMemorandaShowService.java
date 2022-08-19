package acme.features.chef.memoranda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefMemorandaShowService implements AbstractShowService<Chef, Memorandum>{
	
	@Autowired
	protected ChefMemorandaRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		
		int memorandumId, userId;
		Memorandum memorandum;
		boolean result;
		
		
		userId = request.getPrincipal().getActiveRoleId();
		memorandumId = request.getModel().getInteger("id");
		
		memorandum = this.repository.getMemorandumById(memorandumId);
		
		result = memorandum.getFineDish().getChef().getId() == userId;
		
		return result;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;
		
		Memorandum result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = (Memorandum) this.repository.getOne(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		model.setAttribute("fineDishEpicureUsername", entity.getFineDish().getEpicure().getUserAccount().getUsername());
		
		request.unbind(entity, model, "automaticSeqNum", "instantiationMoment", "report", "link");
	}

}
