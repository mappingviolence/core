package org.mappingviolence.poi.attribute;

import org.mappingviolence.form.FormField;
import org.mappingviolence.form.SimpleFormField;
import org.mappingviolence.poi.date.Date;

public class DateFormField extends SimpleFormField<Date> implements FormField<Date> {

  @SuppressWarnings("unused")
  private DateFormField() {
  }

  public DateFormField(Date date) {
    super("Date", date);
  }

  @Override
  public boolean isValidValue() {
    return getValue().isValid();
  }

}
