package acme.features.chef.ketema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Ketema;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefKetemaDeleteService implements AbstractDeleteService<Chef, Ketema>{

	@Autowired
	protected ChefKetemaRepository repository;
	
	@Override
	public boolean authorise(final Request<Ketema> request) {
		
		assert request != null;
		
		boolean result;
		int ketemaId;
		Ketema ketema;
		Chef chef;
		Item item;
		
		ketemaId = request.getModel().getInteger("id");
		ketema = this.repository.findKetemaById(ketemaId);
		item = ketema.getItem();
		chef = item.getChef();
		result = request.isPrincipal(chef);
		result = result && !item.getPublished();		
		
		return result;
		
	}
	
	@Override
	public void bind(final Request<Ketema> request, final Ketema entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "theme", "keylet", "statement", "allotment", "moreInfo", "instantationMoment", "item.name");		
		
	}
	
	@Override
	public void unbind(final Request<Ketema> request, final Ketema entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "theme", "keylet", "statement", "allotment", "moreInfo", "instantationMoment", "item.name");
	}
	
	@Override
	public Ketema findOne(final Request<Ketema> request) {
		 
		assert request != null;
		
		Ketema result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findKetemaById(id);
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Ketema> request, final Ketema entity, final Errors errors) {
		
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void delete(final Request<Ketema> request, final Ketema entity) {
		
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}

}
