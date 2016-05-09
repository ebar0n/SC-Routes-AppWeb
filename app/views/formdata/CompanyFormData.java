package views.formdata;

import models.Company;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class CompanyFormData {

  public String name = "";

  /** Required for form instantiation. */
  public CompanyFormData() {
  }

  /**
   * Creates an initialized form instance. Assumes the passed data is valid. 
   * @param name The name.
   */
  public CompanyFormData(String name) {
    this.name = name;
  }

  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();

    if (name == null || name.length() == 0) {
      errors.add(new ValidationError("name", "No name was given."));
    }
    
    if(errors.size() > 0)
      return errors;

    return null;
  }
}