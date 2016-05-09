package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import models.User;
import models.Street;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import service.UserProvider;
import views.html.*;
import views.formdata.StreetFormData;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Restrict(@Group(StreetController.USER_ROLE))
public class StreetController extends Controller {

	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";

	private final PlayAuthenticate auth;

	private final MyUsernamePasswordAuthProvider provider;

	private final UserProvider userProvider;

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

	@Inject
	public StreetController(final PlayAuthenticate auth, final MyUsernamePasswordAuthProvider provider,
					   final UserProvider userProvider) {
		this.auth = auth;
		this.provider = provider;
		this.userProvider = userProvider;
	}

	public Result index(long id) {
		StreetFormData streetData = (id == 0) ? new StreetFormData() : models.Street.makeStreetFormData(id);
    	Form<StreetFormData> formData = Form.form(StreetFormData.class).fill(streetData);
		return ok(street_index.render(this.userProvider, formData, id));
	}

	public Result submit() {
		Form<StreetFormData> filledForm = Form.form(StreetFormData.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			return badRequest(street_index.render(this.userProvider, filledForm, 0L));
		} else {
			Street street = Street.makeInstance(0L, filledForm.get());
      		flash("success", "Street instance created: " + street);
			return redirect(routes.StreetController.list());
		}
	}

	public Result update(long id) {
		Form<StreetFormData> filledForm = Form.form(StreetFormData.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			return badRequest(street_index.render(this.userProvider, filledForm, id));
		} else {
			Street street = Street.makeInstance(id, filledForm.get());
      		flash("success", "Street instance update: " + street);
			return redirect(routes.StreetController.list());
		}
	}

	public Result list() {
		List<Street> streets = Street.find.all();
		return ok(street_list.render(this.userProvider, streets));
	}

	public Result delete(long id) {
		Street street = models.Street.getStreet(id);
		street.delete();
		return redirect(routes.StreetController.list());
	}

}