package org.mappingviolence.poi.attribute;

import org.mappingviolence.form.SimpleFormField;
import org.mongodb.morphia.geo.Point;
import org.mongodb.morphia.geo.PointBuilder;

public class Location extends SimpleFormField<Point> {
  @SuppressWarnings("unused")
  private Location() {
    super();
  }

  public Location(Double lat, Double lng) {
    super("Location", buildPoint(lat, lng));
  }

  public Location(Point point) {
    super("Location", point);
  }

  public static Point buildPoint(Double lat, Double lng) {
    return PointBuilder.pointBuilder().latitude(lat).longitude(lng).build();
  }

  // TODO
  public static boolean isValidLocation(Double lat, Double lng) {
    return true;
  }
}
