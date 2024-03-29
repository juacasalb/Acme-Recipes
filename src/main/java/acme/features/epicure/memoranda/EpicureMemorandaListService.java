package acme.features.epicure.memoranda;

import java.text.DateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandaListService implements AbstractListService<Epicure, Memorandum>{
	
	@Autowired
	protected EpicureMemorandaRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;
		
		int epicureId;
		Collection<Memorandum> result;
		
		epicureId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findByEpicureId(epicureId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		DateFormat formatter;
		formatter = DateFormat.getInstance();
		
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
		model.setAttribute("fineDishChefUsername", entity.getFineDish().getChef().getUserAccount().getUsername());
		model.setAttribute("instantiationMoment", formatter.format(entity.getInstantiationMoment()));
		
		request.unbind(entity, model, "automaticSeqNum");
	}

}
