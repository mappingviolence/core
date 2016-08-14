package org.mappingviolence.google_api;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleHelperObjects {
  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

  public static HttpTransport getHttpTransport() {
    return HTTP_TRANSPORT;
  }

  public static JsonFactory getJsonFactory() {
    return JSON_FACTORY;
  }
}