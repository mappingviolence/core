package org.mappingviolence.poi.identity;

import org.mappingviolence.form.FormField;

public interface Identity<T> extends FormField<T> {
  public String getCategory();

  @Override
  public T getValue();
}
