package acme.features.administrator.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.features.authenticated.bulletin.AuthenticatedBulletinRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

		@Autowired
		protected AuthenticatedBulletinRepository repository;


		@Override
		public boolean authorise(final Request<Bulletin> request) {
			assert request != null;

			return true;
		}
		
		@Override
		public Bulletin instantiate(final Request<Bulletin> request) {
			assert request != null;
			Bulletin res;
			res = new Bulletin();
			return res;
		}

		
		@Override
		public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			Date moment;
			moment = new Date(System.currentTimeMillis() - 1);
			request.bind(entity, errors, "heading", "text", "flag","link");
			entity.setInstantiationMoment(moment);
		}

		
		@Override
		public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "instantiationMoment", "heading","text","flag","link");
			model.setAttribute("confirm", "false");
		}
		
		@Override
		public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			final Boolean confirmed = request.getModel().getBoolean("confirm");
			errors.state(request, confirmed, "confirm", "administrator.bulletin.form.error.must-confirm");
			
		}

		@Override
		public void create(final Request<Bulletin> request, final Bulletin entity) {
			assert request != null;
			assert entity != null;
			
			Boolean flag;

			flag = request.getModel().getBoolean("flag");

			
			entity.setFlag(flag);
			this.repository.save(entity);
		}
}