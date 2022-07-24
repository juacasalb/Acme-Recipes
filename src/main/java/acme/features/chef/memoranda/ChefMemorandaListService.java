package acme.features.chef.memoranda;

import java.text.DateFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandaListService implements AbstractListService<Chef, Memorandum>{
	
	@Autowired
	protected ChefMemorandaRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;
		
		int chefId;
		Collection<Memorandum> result;
		
		chefId = request.getPrincipal().getAccountId();
		result = this.repository.findByChefId(chefId);
		
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
		model.setAttribute("fineDishEpicureUsername", entity.getFineDish().getEpicure().getUserAccount().getUsername());
		model.setAttribute("instantiationMoment", formatter.format(entity.getInstantiationMoment()));
		
		request.unbind(entity, model, "automaticSeqNum");
	}

}
