package org.mappingviolence.markdown;

public class Markdown {
  public static int wordCount(String str) {
    String[] split = str.split("[\\s]");
    return split.length;
  }
}
