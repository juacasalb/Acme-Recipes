package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	@Autowired
	protected AnyItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}
	
	@Override 
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result;
		int id;
		id = request.getModel().getInteger("id");
		
		result = this.repository.findItemById(id);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "unit", "code", "description", "retailPrice", "link", "type");
	}
	
}
