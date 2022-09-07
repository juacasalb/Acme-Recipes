package acme.features.any.peep;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyPeepListService implements AbstractListService<Any, Peep>{
	
	@Autowired
	protected AnyPeepRepository repository;
	
	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Peep> findMany(final Request<Peep> request) {
		assert request != null;
		
		List<Peep> result;
		Collection<Peep> aux;
		
		Date oneMonthOld;
//		oneMonthOld = DateUtils.addMonths(Date.from(Instant.now()), -1);
		oneMonthOld = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
				
		aux = this.repository.getAllPeeps();
		result = aux.stream().filter(x->x.getInstantationMoment().after(oneMonthOld)).collect(Collectors.toList());
		
		return result;
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "instantationMoment", "heading", "writer", "pieceOfText", "email");
	}

}
