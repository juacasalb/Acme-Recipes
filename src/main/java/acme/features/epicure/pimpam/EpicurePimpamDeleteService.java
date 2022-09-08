//package acme.features.epicure.quittel;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.Quittel;
//import acme.framework.components.models.Model;
//import acme.framework.controllers.Errors;
//import acme.framework.controllers.Request;
//import acme.framework.services.AbstractDeleteService;
//import acme.roles.Epicure;
//
//@Service
//public class EpicureQuittelDeleteService implements AbstractDeleteService<Epicure, Quittel>{
//
//	@Autowired
//	protected EpicureQuittelRepository repository;
//	
//	@Override
//	public boolean authorise(final Request<Quittel> request) {
//		
//		assert request != null;
//		
//		boolean result;
//		int quittelId;
//		Quittel quittel;
//		Epicure epicure;
//		
//		quittelId = request.getModel().getInteger("id");
//		quittel = this.repository.findQuittelById(quittelId);
//		epicure = quittel.getItem().getEpicure();
//		result = request.isPrincipal(epicure);
//		
//		return result;
//		
//	}
//	
//	@Override
//	public void bind(final Request<Quittel> request, final Quittel entity, final Errors errors) {
//		
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//		request.bind(entity, errors, "title", "code", "description", "helping", "link", "instantationMoment", "item.name");		
//		
//	}
//	
//	@Override
//	public void unbind(final Request<Quittel> request, final Quittel entity, final Model model) {
//		
//		assert request != null;
//		assert entity != null;
//		assert model != null;
//
//		request.unbind(entity, model, "title", "code", "description", "helping", "link", "instantationMoment", "item.name");
//	}
//	
//	@Override
//	public Quittel findOne(final Request<Quittel> request) {
//		 
//		assert request != null;
//		
//		Quittel result;
//		int id;
//		
//		id = request.getModel().getInteger("id");
//		result = this.repository.findQuittelById(id);
//		
//		return result;
//		
//	}
//	
//	@Override
//	public void validate(final Request<Quittel> request, final Quittel entity, final Errors errors) {
//		
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//		
//	}
//	
//	@Override
//	public void delete(final Request<Quittel> request, final Quittel entity) {
//		
//		assert request != null;
//		assert entity != null;
//		
//		this.repository.delete(entity);
//		
//	}
//
//}
