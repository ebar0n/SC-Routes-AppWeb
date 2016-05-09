package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.ExpressionList;
import models.TokenAction.Type;

import play.Logger;
import play.data.format.Formats;
import play.data.validation.Constraints;
import views.formdata.RoutesCompanyFormData;

import javax.persistence.*;
import java.util.*;

/**
 * Initial version based on work by Steve Chaloner (steve@objectify.be) for
 * Deadbolt2
 */
@Entity
@Table(name = "routescompany")
public class RoutesCompany extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@ManyToOne
	@Constraints.Required
	public Company company;
	
	@Constraints.Required	
	public float price;

	@ManyToOne
	@Constraints.Required
    public Street initialRoutesCompany;
	
	@ManyToMany
	public List<Street> mainRoutesCompany;

	@ManyToOne
	@Constraints.Required
    public Street finalRoutesCompany;

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return this.company.name + " - " + this.price;
    }

    public boolean equals(Object obj) { 
        return this.id == ((RoutesCompany)obj).id; 
    }

    public static final Finder<Long, RoutesCompany> find = new Finder<Long, RoutesCompany>(
            Long.class, RoutesCompany.class);

    public static RoutesCompany getRoutesCompany(long id) {
        final ExpressionList<RoutesCompany> exp = find.where().eq("id", id);
        if (exp.findRowCount() > 0){
            return exp.findUnique();
        }
        else{
            throw new RuntimeException("Couldn't find routesCompany");
        }
    }

    public static RoutesCompanyFormData makeRoutesCompanyFormData(long id) {
        RoutesCompany routesCompany = getRoutesCompany(id);
        return new RoutesCompanyFormData(
            routesCompany.company,
            routesCompany.price,
            routesCompany.initialRoutesCompany,
            routesCompany.mainRoutesCompany,
            routesCompany.finalRoutesCompany
        );
    }

    public static RoutesCompany makeInstance(Long id, RoutesCompanyFormData formData) {
        RoutesCompany routesCompany = (id == 0L) ? new RoutesCompany(): getRoutesCompany(id);
        routesCompany.company = Company.getCompany(formData.company);
        routesCompany.price = formData.price;
        routesCompany.initialRoutesCompany = Street.getStreet(formData.initialRoutesCompany);
        routesCompany.finalRoutesCompany = Street.getStreet(formData.finalRoutesCompany);
        routesCompany.save();

        routesCompany = getRoutesCompany(routesCompany.id);
        routesCompany.mainRoutesCompany.clear();
        for (String mainRoutesCompany: formData.mainRoutesCompany) {
            routesCompany.mainRoutesCompany.add(Street.getStreet(mainRoutesCompany));
        }
        routesCompany.save();
        return routesCompany;
    }

}
