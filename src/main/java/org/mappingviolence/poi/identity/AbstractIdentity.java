package org.mappingviolence.poi.identity;

import org.mappingviolence.form.SimpleFormField;

public class AbstractIdentity<T> extends SimpleFormField<T> implements Identity<T> {

  private String category;

  protected AbstractIdentity() {
  }

  public AbstractIdentity(String category, T value) {
    super(category, value);
    this.category = category;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof String) {
      return o.equals(this.getId());
    } else if (o instanceof AbstractIdentity) {
      return ((AbstractIdentity<?>) o).getId().equals(this.getId());
    } else {
      return false;
    }
  }

}
