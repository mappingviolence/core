package org.mappingviolence.poi;

import org.bson.types.ObjectId;
import org.mappingviolence.user.User;
import org.mappingviolence.wiki.Version;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "pois")
public class POIVersion extends Version<POI> {
  @Id
  private String id;

  private POI data;

  @SuppressWarnings("unused")
  private POIVersion() {
    super();
  }

  public POIVersion(User editor, POI data) {
    super(editor);
    id = new ObjectId().toHexString();
    this.data = data;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public POI getData() {
    return data;
  }
}
