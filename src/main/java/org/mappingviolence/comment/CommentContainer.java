package org.mappingviolence.comment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface CommentContainer {
  public Map<Field, List<Comment>> getComments();

  public List<Comment> getComments(String attributeId);

  public static List<Comment> getComments(Object obj, String attributeId) {
    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (Arrays.asList(field.getType().getInterfaces()).contains(Commentable.class)) {
        try {
          Object fieldVal = field.get(obj);
          Commentable attribute = Commentable.class.cast(fieldVal);
          if (attributeId.equals(attribute.getId())) {
            return attribute.getComments();
          }
        } catch (IllegalAccessException | ClassCastException e) {
          e.printStackTrace();
        }
      }
    }
    for (Field field : fields) {
      if (Arrays.asList(field.getType().getInterfaces()).contains(CommentContainer.class)) {
        List<Comment> comments;
        try {
          comments = CommentContainer.getComments(field.get(obj), attributeId);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          e.printStackTrace();
          return null;
        }
        if (comments != null) {
          return comments;
        }
      }
    }
    return null;
  }
}
