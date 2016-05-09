package views.formdata;

import models.Suggestions;
import models.Company;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsFormData {

  public String firstName = "";
  public String lastName = "";
  public String email = "";
  public String phone = "";
  public String company = "";
  public String comment = "";

  /** Required for form instantiation. */
  public SuggestionsFormData() {
  }

  public SuggestionsFormData(String firstName, String lastName, String email, String phone, Company company, String comment) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.company = company.name;
    this.comment = comment;
  }

  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();
    
    if (firstName == null || firstName.length() == 0) {
      errors.add(new ValidationError("firstName", "No name was given."));
    }

    if (lastName == null || lastName.length() == 0) {
      errors.add(new ValidationError("lastName", "No name was given."));
    }

    if (email == null || email.length() == 0) {
      errors.add(new ValidationError("email", "No name was given."));
    }

    if (phone == null || phone.length() == 0) {
      errors.add(new ValidationError("phone", "No name was given."));
    }

    if (comment == null || comment.length() == 0) {
      errors.add(new ValidationError("comment", "No name was given."));
    }

    if(errors.size() > 0)
      return errors;

    return null;
  }
}