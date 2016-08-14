package org.mappingviolence.wiki;

import java.util.Date;

import org.mappingviolence.user.User;

public abstract class Version<T> {
  private User editor;
  private Date dateModified;

  protected Version() {
    dateModified = new Date();
  }

  public Version(User editor) {
    this();
    this.editor = editor;
  }

  public abstract String getId();

  public abstract T getData();

  public User getEditor() {
    return editor;
  }

  public Date getDateModified() {
    return dateModified;
  }
}
