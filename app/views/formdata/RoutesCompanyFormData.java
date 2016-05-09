package views.formdata;

import models.RoutesCompany;
import models.Company;
import models.Street;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class RoutesCompanyFormData {

  public String company = "";
  public float price = 0;
  public String initialRoutesCompany = "";
  public List<String> mainRoutesCompany = new ArrayList<>();
  public String finalRoutesCompany = "";

  /** Required for form instantiation. */
  public RoutesCompanyFormData() {
  }

  public RoutesCompanyFormData(Company company, float price, Street initialRoutesCompany, List<Street> mainRoutesCompany, Street finalRoutesCompany) {
    this.company = company.name;
    this.price = price;
    this.initialRoutesCompany = initialRoutesCompany.name;
    for(Street street : mainRoutesCompany) {
      this.mainRoutesCompany.add(street.name);
    }
    this.finalRoutesCompany = finalRoutesCompany.name;
  }

  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList<>();

    if (company == null || company.length() == 0) {
      errors.add(new ValidationError("company", "No name was given."));
    } else if (Company.existsCompany(company) == false) {
      errors.add(new ValidationError("company", "Invalid Company: " + company + "."));
    }

    if (price == 0) {
      errors.add(new ValidationError("price", "No name was given."));
    }

    if (initialRoutesCompany == null || initialRoutesCompany.length() == 0) {
      errors.add(new ValidationError("initialRoutesCompany", "No name was given."));
    } else if (Street.existsStreet(initialRoutesCompany) == false) {
      errors.add(new ValidationError("initialRoutesCompany", "Invalid Street: " + initialRoutesCompany + "."));
    }

    if (mainRoutesCompany.size() > 0) {
      for (String routes : mainRoutesCompany) {
        if (Street.existsStreet(routes) == false) {
          errors.add(new ValidationError("mainRoutesCompany", "Unknown Street: " + routes + "."));
        }
      }
    }

    if (finalRoutesCompany == null || finalRoutesCompany.length() == 0) {
      errors.add(new ValidationError("finalRoutesCompany", "No name was given."));
    } else if (Street.existsStreet(finalRoutesCompany) == false) {
      errors.add(new ValidationError("finalRoutesCompany", "Invalid Street: " + finalRoutesCompany + "."));
    }
    
    if(errors.size() > 0)
      return errors;

    return null;
  }
}