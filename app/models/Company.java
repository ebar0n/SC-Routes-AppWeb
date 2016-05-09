package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.ExpressionList;
import models.TokenAction.Type;
import play.data.format.Formats;
import play.data.validation.Constraints;
import views.formdata.CompanyFormData;
import views.formdata.SuggestionsFormData;

import javax.persistence.*;
import java.util.*;

/**
 * Initial version based on work by Steve Chaloner (steve@objectify.be) for
 * Deadbolt2
 */
@Entity
@Table(name = "company")
public class Company extends Model{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String name = "--------------";

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return this.name;
    }

    public static final Finder<Long, Company> find = new Finder<Long, Company>(
            Long.class, Company.class);

    public static boolean existsCompany(long id) {
        final ExpressionList<Company> exp = find.where().eq("id", id);
        return exp.findRowCount() > 0;
    }

    public static boolean existsCompany(String name) {
        final ExpressionList<Company> exp = find.where().eq("name", name);
        return exp.findRowCount() > 0;
    }

    public static Company getCompany(long id) {
        final ExpressionList<Company> exp = find.where().eq("id", id);
        if (exp.findRowCount() > 0){
            return exp.findUnique();
        }
        else{
            throw new RuntimeException("Couldn't find company");
        }
    }

    public static Company getCompany(String name) {
        final ExpressionList<Company> exp = find.where().eq("name", name);
        if (exp.findRowCount() > 0){
            return exp.findUnique();
        }
        else{
            throw new RuntimeException("Couldn't find company");
        }
    }

    public static CompanyFormData makeCompanyFormData(long id) {
        return new CompanyFormData(getCompany(id).name);
    }

    public static Company makeInstance(Long id, CompanyFormData formData) {
        Company company = (id == 0L) ? new Company(): getCompany(id);
        company.name = formData.name;
        company.save();
        return company;
    }

}
