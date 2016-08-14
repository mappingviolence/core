package org.mappingviolence.poi.identity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.mappingviolence.comment.Comment;
import org.mappingviolence.comment.CommentContainer;

public class Person implements CommentContainer {
  private Collection<SimpleIdentity> identities;

  public Person() {
    identities = new ArrayList<>();
  }

  public <T> boolean addIdentity(SimpleIdentity newIdentity) {
    return identities.add(newIdentity);
  }

  public <T> boolean removeIdentity(SimpleIdentity identity) {
    return identities.remove(identity);
  }

  public void setIdentities(Collection<SimpleIdentity> identities) {
    this.identities = identities;
  }

  public Collection<SimpleIdentity> getIdentities() {
    return identities;
  }

  @Override
  public Map<Field, List<Comment>> getComments() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Comment> getComments(String attributeId) {
    return CommentContainer.getComments(this, attributeId);
  }
}
