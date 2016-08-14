package org.mappingviolence.database;

import org.mappingviolence.wiki.WikiPage;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DatabaseConnection {
  private static final MongoClient CLIENT;
  private static final Morphia MORPHIA;

  static {
    try {
      // CLIENT = new MongoClient(new ServerAddress("0.tcp.ngrok.io", 14903));
      CLIENT = new MongoClient(new ServerAddress("utra.mappingviolence.org", 27017));
      MORPHIA = new Morphia();

      // tell Morphia where to find your classes
      // can be called multiple times with different packages or classes
      // TODO
      MORPHIA.mapPackage("org.mappingviolence.database");
      MORPHIA.mapPackage("org.mappingviolence.user");
      MORPHIA.mapPackage("org.mappingviolence.poi");
      MORPHIA.mapPackage("org.mappingviolence.poi.attribute");
      MORPHIA.mapPackage("org.mappingviolence.poi.identity");
      MORPHIA.mapPackage("org.mappingviolence.wiki");
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private DatabaseConnection() {
  }

  public static Datastore getDatabase(String dbName) {
    // create the Datastore connecting to the default port on the local host
    Datastore datastore = MORPHIA.createDatastore(CLIENT, dbName);
    datastore.createQuery(WikiPage.class).filter("creator =", null);
    datastore.ensureIndexes();
    return datastore;
  }
}
