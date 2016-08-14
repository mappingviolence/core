package org.mappingviolence.poi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mappingviolence.database.DatabaseConnection;
import org.mappingviolence.user.User;
import org.mappingviolence.wiki.Version;
import org.mappingviolence.wiki.WikiPage;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "poi-pages")
public class POIWikiPage extends WikiPage<POI> {
  private static final Datastore ds = DatabaseConnection.getDatabase("data-entry-wiki");

  @Id
  private String id;

  @Reference
  private POIVersion current;

  @Reference
  private LinkedList<POIVersion> previous;

  @SuppressWarnings("unused")
  private POIWikiPage() {
    super();
  }

  public POIWikiPage(User creator) {
    super(creator);
    id = new ObjectId().toHexString();
    current = new POIVersion(creator, new POI());
    ds.save(current);
    previous = new LinkedList<>();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Version<POI> getCurrentVersion() {
    return current;
  }

  @Override
  public List<Version<POI>> getPreviousVersions() {
    // List<Version<POI>> previousList = new ArrayList<>();
    // ds.getByKeys(previous).forEach((POIVersion poiV) -> {
    // previousList.add(poiV);
    // });
    // return previousList;
    List<Version<POI>> previousList = new ArrayList<>();
    previous.forEach((POIVersion poiV) -> {
      previousList.add(poiV);
    });
    return previousList;
  }

  @Override
  public void addVersion(User editor, POI newPOI) {
    POIVersion newPOIVersion = new POIVersion(editor, newPOI);
    /* Make sure that newVersion is saved to db */
    ds.save(newPOIVersion);
    /*
     * Alternatively, we could check if it's in the db.
     * ds.exists(keyOrEntity)
     * Then based on that add it or continue
     */
    /* Add current version to list of previous versions */
    if (current != null) {
      previous.addFirst(current);
    }
    /* Set current to newVersion */
    current = newPOIVersion;
  }
}
