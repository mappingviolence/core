package org.mappingviolence.poi.identity;

public class SimpleIdentity extends AbstractIdentity<String> implements Identity<String> {

  protected SimpleIdentity() {
  }

  public SimpleIdentity(String category, String value) {
    super(category, value);
  }

}
