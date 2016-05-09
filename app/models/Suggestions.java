package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.ExpressionList;
import models.TokenAction.Type;
import play.data.format.Formats;
import play.data.validation.Constraints;
import views.formdata.SuggestionsFormData;
import models.Company;

import javax.persistence.*;
import java.util.*;

/**
 * Initial version based on work by Steve Chaloner (steve@objectify.be) for
 * Deadbolt2
 */
@Entity
@Table(name = "suggestions")
public class Suggestions extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Constraints.Required
    @Column(length=50)
    public String firstName;

    @Constraints.Required
    @Column(length=50)
	public String lastName;

	@Constraints.Required
	@Column(length=100)
	public String email;

	@Constraints.Required
	@Column(length=20)
	public String phone;

	@ManyToOne(optional=true)
	public Company company;

	@Constraints.Required
	@Column(length=1000)
	public String comment;

	@CreatedTimestamp
	public Date createdAt;

	public String toString() {
        return this.comment + " " + this.createdAt;
    }

	public static final Finder<Long, Suggestions> find = new Finder<Long, Suggestions>(
			Long.class, Suggestions.class);


    public static Suggestions makeInstance(SuggestionsFormData formData) {
        Suggestions suggestions = new Suggestions();
        suggestions.firstName = formData.firstName;
        suggestions.lastName = formData.lastName;
        suggestions.email = formData.email;
        suggestions.phone = formData.phone;
        if (Company.existsCompany(formData.company)){
        	suggestions.company = Company.getCompany(formData.company);
        } else {
        	suggestions.company = null;
        }
        suggestions.comment = formData.comment;
        suggestions.createdAt = new Date();
        suggestions.save();
        return suggestions;
    }

}
