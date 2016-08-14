package org.mappingviolence.comment;

import java.util.UUID;

import org.mappingviolence.user.User;

public class Comment {
  private final String id;

  private User author;

  private String commentText;

  private Comment() {
    id = UUID.randomUUID().toString();
  }

  public Comment(User author) {
    this();
    if (author == null) {
      throw new IllegalArgumentException("author cannot be null");
    }
    this.author = author;
  }

  public Comment(User author, String commentText) {
    this(author);
    if (commentText == null) {
      throw new IllegalArgumentException("commentText cannot be null");
    }
    this.commentText = commentText;
  }

  public String getId() {
    return id;
  }

  public User getAuthor() {
    return author;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof String) {
      return o.equals(this.id);
    } else if (o instanceof Comment) {
      return ((Comment) o).id.equals(this.id);
    } else {
      return false;
    }
  }
}
