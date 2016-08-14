package org.mappingviolence.attribute;

public interface Attribute<T> {
  public String getName();

  public T getValue();

  public void setValue(T value);

  /*
   * Used to check if that value is valid to save into
   * the database
   */
  public boolean isValidValue();
}
