package org.mappingviolence.attribute;

public class SimpleAttribute<T> implements Attribute<T> {
  private String name;
  private T value;

  protected SimpleAttribute() {
  }

  public SimpleAttribute(String name, T value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String getName() {
    return name;
  }

  protected void setName(String name) {
    this.name = name;
  }

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  /**
   * SimpleAttribute assumes that the entered value (data) for the
   * attribute is always valid. That is, any value (data) is permitted
   * to be saved to the database.
   * 
   * @see org.mappingviolence.attribute.Attribute#isValidValue()
   */
  @Override
  public boolean isValidValue() {
    return true;
  }
}
