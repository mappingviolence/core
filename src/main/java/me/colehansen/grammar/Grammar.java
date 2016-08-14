package me.colehansen.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Grammar<T> {

  private List<Parser<T>> parsers;

  private Grammar() {
    this.parsers = new ArrayList<>();
  }

  public T parse(String stringToParse) {
    for (Parser<T> parser : parsers) {
      Pattern pattern = parser.pattern;
      if (pattern.matcher(stringToParse).matches()) {
        String[] strings = stringToParse.split(" ");
        return parser.function.parse(strings);
      }
    }
    return null;
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  public static class Builder<T> {
    private List<Parser<T>> parsers;

    public Builder() {
      parsers = new ArrayList<>();
    }

    public Builder<T> addGrammar(String patternStr, ParserFunction<T> parserFunction) {
      Pattern pattern = Pattern.compile(patternStr);
      Parser<T> parser = new Parser<>(pattern, parserFunction);
      parsers.add(parser);
      return this;
    }

    public Grammar<T> build() {
      Grammar<T> g = new Grammar<>();
      g.parsers = this.parsers;
      return g;
    }
  }

  private static class Parser<T> {
    private Pattern pattern;
    private ParserFunction<T> function;

    public Parser(Pattern pattern, ParserFunction<T> function) {
      this.pattern = pattern;
      this.function = function;
    }
  }
}
