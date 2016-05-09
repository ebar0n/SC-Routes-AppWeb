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
import views.formdata.StreetFormData;

import javax.persistence.*;
import java.util.*;

/**
 * Initial version based on work by Steve Chaloner (steve@objectify.be) for
 * Deadbolt2
 */
@Entity
@Table(name = "street")
public class Street extends Model{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return this.name;
    }

    public static final Finder<Long, Street> find = new Finder<Long, Street>(
            Long.class, Street.class);

    public static boolean existsStreet(long id) {
        final ExpressionList<Street> exp = find.where().eq("id", id);
        return exp.findRowCount() > 0;
    }

    public static boolean existsStreet(String name) {
        final ExpressionList<Street> exp = find.where().eq("name", name);
        return exp.findRowCount() > 0;
    }

    public static Street getStreet(long id) {
        final ExpressionList<Street> exp = find.where().eq("id", id);
        if (exp.findRowCount() > 0){
            return exp.findUnique();
        }
        else{
            throw new RuntimeException("Couldn't find Street");
        }
    }

    public static Street getStreet(String name) {
        final ExpressionList<Street> exp = find.where().eq("name", name);
        if (exp.findRowCount() > 0){
            return exp.findUnique();
        }
        else{
            throw new RuntimeException("Couldn't find Street");
        }
    }

    public static StreetFormData makeStreetFormData(long id) {
        return new StreetFormData(getStreet(id).name);
    }

    public static Street makeInstance(Long id, StreetFormData formData) {
        Street street = (id == 0L) ? new Street(): getStreet(id);
        street.name = formData.name;
        street.save();
        return street;
    }

}
