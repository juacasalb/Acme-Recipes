package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep>{
	@Autowired
	protected AnyPeepRepository repository;

	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "heading", "writer", "pieceOfText", "email");
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "heading", "writer", "pieceOfText", "email");
		model.setAttribute("confirm", false);
		
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		final Peep peep = new Peep();
		peep.setInstantationMoment(new Date(System.currentTimeMillis()));
		return peep;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		if(!errors.hasErrors("confirm")) {
			final Boolean confirm = request.getModel().getAttribute("confirm", Boolean.class);
			errors.state(request, confirm, "confirm", "form.peep.confirm");
		}
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		this.repository.save(entity);
		
	}

}
