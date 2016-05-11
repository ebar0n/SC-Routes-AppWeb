package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import models.User;
import models.Suggestions;
import models.Company;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import service.UserProvider;
import views.html.*;
import views.formdata.SuggestionsFormData;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SuggestionsController extends Controller {

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
	public SuggestionsController(final PlayAuthenticate auth, final MyUsernamePasswordAuthProvider provider,
					   final UserProvider userProvider) {
		this.auth = auth;
		this.provider = provider;
		this.userProvider = userProvider;
	}

	public Result index() {
		SuggestionsFormData suggestionsData = new SuggestionsFormData();
    	Form<SuggestionsFormData> formData = Form.form(SuggestionsFormData.class).fill(suggestionsData);
    	List<Company> companies = Company.find.all();
    	companies.add(0, new Company());
		return ok(suggestions_index.render(this.userProvider, formData, companies));
	}

	public Result submit() {
		Form<SuggestionsFormData> filledForm = Form.form(SuggestionsFormData.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			List<Company> companies = Company.find.all();
			companies.add(0, new Company());
			flash("error", "Please correct errors above.");
			return badRequest(suggestions_index.render(this.userProvider, filledForm, companies));
		} else {
			Suggestions suggestions = Suggestions.makeInstance(filledForm.get());
      		flash("success", "Suggestions instance created: " + suggestions);
			return redirect(routes.Application.index());
		}
	}

	@Restrict(@Group(SuggestionsController.USER_ROLE))
	public Result list() {
		List<Suggestions> suggestionss = Suggestions.find.all();
		return ok(suggestions_list.render(this.userProvider, suggestionss));
	}

}