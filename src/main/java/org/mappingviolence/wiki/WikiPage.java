package org.mappingviolence.wiki;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mappingviolence.comment.Comment;
import org.mappingviolence.comment.CommentContainer;
import org.mappingviolence.user.User;

public abstract class WikiPage<T extends CommentContainer> implements CommentContainer {
  private User creator;
  private Date dateCreated;
  private Status status;

  protected WikiPage() {
    dateCreated = new Date();
    status = Status.DRAFT;
  }

  public WikiPage(User creator) {
    this();
    this.creator = creator;
  }

  public abstract String getId();

  public User getCreator() {
    return creator;
  }

  public User getLastEditor() {
    return getCurrentVersion().getEditor();
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public Date getDateLastModified() {
    return getCurrentVersion().getDateModified();
  }

  public abstract Version<T> getCurrentVersion();

  public T getCurrentData() {
    return getCurrentVersion().getData();
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public abstract List<Version<T>> getPreviousVersions();

  public abstract void addVersion(User editor, T newVersion);

  @Override
  public Map<Field, List<Comment>> getComments() {
    return getCurrentVersion().getData().getComments();
  }

  @Override
  public List<Comment> getComments(String attributeId) {
    return CommentContainer.getComments(this, attributeId);
  }
}
