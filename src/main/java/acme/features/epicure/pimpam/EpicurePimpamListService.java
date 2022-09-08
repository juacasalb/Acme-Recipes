//package acme.features.epicure.quittel;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.Quittel;
//import acme.framework.components.models.Model;
//import acme.framework.controllers.Request;
//import acme.framework.services.AbstractListService;
//import acme.roles.Epicure;
//
//@Service
//public class EpicureQuittelListService implements AbstractListService<Epicure, Quittel>{
//
//	@Autowired
//	protected EpicureQuittelRepository repository;
//	
//	@Override
//	public boolean authorise(final Request<Quittel> request) {
//		
//		assert request != null;
//		
//		return true;
//		
//	}
//	
//	@Override
//	public Collection<Quittel> findMany(final Request<Quittel> request){
//		
//		assert request != null;
//		
//		final int getId = request.getPrincipal().getActiveRoleId();
//		Collection<Quittel> result;
//		result = this.repository.findQuittelsByEpicureId(getId);
//		
//		return result;
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
//		request.unbind(entity, model, "title", "helping", "instantationMoment");
//		
//	}
//	
//		
//
//}
