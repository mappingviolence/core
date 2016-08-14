package org.mappingviolence.form;

public class ValidationError {
  private String message;

  private String formFieldName;

  public ValidationError(String formFieldName, String message) {
    this.message = message;
    this.formFieldName = formFieldName;
  }

  public String getMessage() {
    return message;
  }

  public String getFormFieldName() {
    return formFieldName;
  }
}
