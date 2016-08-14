package me.colehansen.grammar;

@FunctionalInterface
public interface ParserFunction<T> {
  public T parse(String... strings);
}
