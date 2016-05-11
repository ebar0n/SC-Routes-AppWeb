package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import models.User;
import models.Company;
import play.Routes;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import service.UserProvider;
import views.html.*;
import views.formdata.CompanyFormData;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Restrict(@Group(CompanyController.USER_ROLE))
public class CompanyController extends Controller {

	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";

	private final PlayAuthenticate auth;

	private final MyUsernamePasswordAuthProvider provider;

	private final UserProvider userProvider;

	private final Form<CompanyFormData> formData;

	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

	@Inject
	public CompanyController(final PlayAuthenticate auth, final MyUsernamePasswordAuthProvider provider,
					   final UserProvider userProvider, final FormFactory formFactory) {
		this.auth = auth;
		this.provider = provider;
		this.userProvider = userProvider;
		this.formData = formFactory.form(CompanyFormData.class);
	}

	public Result index(long id) {
		CompanyFormData companyData = (id == 0) ? new CompanyFormData() : models.Company.makeCompanyFormData(id);
    	Form<CompanyFormData> formData = this.formData.fill(companyData);
		return ok(company_index.render(this.userProvider, formData, id));
	}

	public Result submit() {
		Form<CompanyFormData> filledForm = this.formData.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			return badRequest(company_index.render(this.userProvider, filledForm, 0L));
		} else {
			Company company = Company.makeInstance(0L, filledForm.get());
      		flash("success", "Company instance created: " + company);
			return redirect(routes.CompanyController.list());
		}
	}

	public Result update(long id) {
		Form<CompanyFormData> filledForm = this.formData.bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			return badRequest(company_index.render(this.userProvider, filledForm, id));
		} else {
			Company company = Company.makeInstance(id, filledForm.get());
      		flash("success", "Company instance update: " + company);
			return redirect(routes.CompanyController.list());
		}
	}

	public Result list() {
		List<Company> companys = Company.find.all();
		return ok(company_list.render(this.userProvider, companys));
	}

	public Result delete(long id) {
		Company company = models.Company.getCompany(id);
		company.delete();
		return redirect(routes.CompanyController.list());
	}

}