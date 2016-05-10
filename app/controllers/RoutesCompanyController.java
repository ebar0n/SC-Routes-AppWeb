package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import models.User;
import models.RoutesCompany;
import models.Street;
import models.Company;
import play.libs.Json;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import service.UserProvider;
import views.html.*;
import views.formdata.RoutesCompanyFormData;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class RoutesCompanyController extends Controller {

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
	public RoutesCompanyController(final PlayAuthenticate auth, final MyUsernamePasswordAuthProvider provider,
					   final UserProvider userProvider) {
		this.auth = auth;
		this.provider = provider;
		this.userProvider = userProvider;
	}

	@Restrict(@Group(RoutesCompanyController.USER_ROLE))
	public Result index(long id) {
		RoutesCompanyFormData routesCompanyData = (id == 0) ? new RoutesCompanyFormData() : models.RoutesCompany.makeRoutesCompanyFormData(id);
    	Form<RoutesCompanyFormData> formData = Form.form(RoutesCompanyFormData.class).fill(routesCompanyData);    	
    	List<Street> streets = Street.find.all();
    	List<Company> companies = Company.find.all();
		return ok(routescompany_index.render(this.userProvider, formData, id, streets, companies));
	}

	@Restrict(@Group(RoutesCompanyController.USER_ROLE))
	public Result submit() {
		Form<RoutesCompanyFormData> filledForm = Form.form(RoutesCompanyFormData.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			List<Street> streets = Street.find.all();
			List<Company> companies = Company.find.all();
			return badRequest(routescompany_index.render(this.userProvider, filledForm, 0L, streets, companies));
		} else {
			models.RoutesCompany routesCompany = models.RoutesCompany.makeInstance(0L, filledForm.get());
      		flash("success", "RoutesCompany instance created: " + routesCompany);
			return redirect(routes.RoutesCompanyController.list());
		}
	}

	@Restrict(@Group(RoutesCompanyController.USER_ROLE))
	public Result update(long id) {
		Form<RoutesCompanyFormData> filledForm = Form.form(RoutesCompanyFormData.class).bindFromRequest();
		if (filledForm.hasErrors()) {
			flash("error", "Please correct errors above.");
			List<Street> streets = Street.find.all();
			List<Company> companies = Company.find.all();
			return badRequest(routescompany_index.render(this.userProvider, filledForm, id, streets, companies));
		} else {
			models.RoutesCompany routesCompany = models.RoutesCompany.makeInstance(id, filledForm.get());
      		flash("success", "RoutesCompany instance update: " + routesCompany);
			return redirect(routes.RoutesCompanyController.list());
		}
	}

	@Restrict(@Group(RoutesCompanyController.USER_ROLE))
	public Result list() {
		List<RoutesCompany> routesCompanys = RoutesCompany.find.all();
		return ok(routescompany_list.render(this.userProvider, routesCompanys));
	}

	@Restrict(@Group(RoutesCompanyController.USER_ROLE))
	public Result delete(long id) {
		models.RoutesCompany routesCompany = models.RoutesCompany.getRoutesCompany(id);
		routesCompany.delete();
		return redirect(routes.RoutesCompanyController.list());
	}

	public List<RoutesCompany> get_routesCompanys(String search){
		List<RoutesCompany> routesCompanys;
		if (search == null || search.length() == 0) {
			routesCompanys = new ArrayList<RoutesCompany>();
		} else { 
			routesCompanys = RoutesCompany.find.where()
				.ilike("initialRoutesCompany.name", "%"+search+"%")
				.findList();

			List<RoutesCompany> routesCompanys2 = RoutesCompany.find.where()
				.ilike("finalRoutesCompany.name", "%"+search+"%")
				.findList();

			List<RoutesCompany> routesCompanys3 = RoutesCompany.find.where()
				.ilike("mainRoutesCompany.name", "%"+search+"%")
				.findList();

			for (RoutesCompany each: routesCompanys2) {
				if (routesCompanys.contains(each) == false) {
					routesCompanys.add(each);
				}
	        }

	        for (RoutesCompany each: routesCompanys3) {
				if (routesCompanys.contains(each) == false) {
					routesCompanys.add(each);
				}
	        }
		}		
		return routesCompanys;
	}

	public Result search(String search){
		List<RoutesCompany> routesCompanys = this.get_routesCompanys(search);
		return ok(routescompany_search.render(this.userProvider, routesCompanys, search));
	}

	public Result searchApi(String search){
		List<RoutesCompany> routesCompanys = this.get_routesCompanys(search);
		return ok(Json.toJson(routesCompanys));
	}

}