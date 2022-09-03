package acme.features.chef.pimpam;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Pimpam;
import acme.features.chef.item.ChefItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam>{

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Autowired
	protected ChefItemRepository itemRepository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {

		assert request != null;
		
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		String code;
		Collection<Pimpam> similarCodes;
		
		moment = new Date(System.currentTimeMillis() - 1);
		code = moment.getYear()%100 + "-" + (moment.getMonth()+1>10?moment.getMonth()+1:"0"+(moment.getMonth()+1)) + "-" +  (moment.getDate()>9?moment.getDate():"0"+moment.getDate());
		similarCodes = this.repository.findPimpamBySimilarCode(code);

		code += "-" + (similarCodes.size()>9?similarCodes.size():"0"+similarCodes.size());
		
		request.bind(entity, errors, "title", "description", "budget", "link", "finishingDate");
		entity.setInstantationMoment(moment);
		entity.setCode(code);
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int chefId = this.repository.findChefById(request.getPrincipal().getActiveRoleId()).getId();
		final Collection<Item> items =  this.repository.findManyAvailableItemsByChef(chefId);
		
		model.setAttribute("items", items);
		
		request.unbind(entity, model, "title", "code", "description", "budget", "link", "finishingDate", "instantationMoment");
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {

		assert request != null;
		
		Pimpam pimpam;
		
		pimpam = new Pimpam();
		
		return pimpam;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;
	
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {

		assert request != null;
		assert entity != null;
		
		entity.setItem(this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
		
		this.repository.save(entity);
		
	}

	
}
