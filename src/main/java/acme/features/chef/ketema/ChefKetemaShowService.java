package acme.features.chef.ketema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Ketema;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefKetemaShowService implements AbstractShowService<Chef, Ketema>{

	@Autowired
	protected ChefKetemaRepository repository;
	
	@Override
	public boolean authorise(final Request<Ketema> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Ketema findOne(final Request<Ketema> request) {
		assert request != null;
	
		Integer id;
		Ketema item;
		id = request.getModel().getInteger("id");
		item = this.repository.findKetemaById(id);
		
		return item;
	}

	@Override
	public void unbind(final Request<Ketema> request, final Ketema entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "theme", "keylet", "statement", "allotment", "moreInfo", "instantationMoment", "finishingDate", "item.name");
		
	}
	
}
