package org.mappingviolence.poi.date;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.google.common.collect.ImmutableList;

import me.colehansen.grammar.Grammar;

// TODO: Rewrite this class
// TODO: Remove error checking on creation, maybe not.
// Idk, let's talk to eddie about this.
// This shouldn't be an entity, but an embedded
public class Date implements Comparable<Date> {

  public boolean isValid() {
    // TODO: Implement this
    return true;
  }

  private Integer year;

  private String month;

  private Integer day;

  private String modifier;

  private static Grammar<Date> dateGrammar;

  private static final Integer MIN_YEAR, MAX_YEAR;

  static {
    Properties prop = new Properties();
    try (InputStream input = Thread
        .currentThread()
        .getContextClassLoader()
        .getResourceAsStream("config/date.properties")) {
      prop.load(input);

      MIN_YEAR = Integer.valueOf(prop.getProperty("min_year"));
      MAX_YEAR = Integer.valueOf(prop.getProperty("max_year"));

    } catch (NumberFormatException e) {
      throw new RuntimeException("min_year key in date.properties is not an integer.");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(
          "Could not find date.properties file. Please make sure it is in the src/main/config directory and is named date.properties.");
    } catch (IOException e) {
      throw new RuntimeException(
          "There was a problem reading from the file. Please try again later.");
    }

    Grammar.Builder<Date> b = Grammar.builder();
    b.addGrammar("[1,2][0-9]{3}", (String[] strs) -> {
      assert strs.length == 1;
      Integer year = Integer.valueOf(strs[0]);
      return Date.builder().setYear(year).build();
    });
    b.addGrammar("(Late|Mid|Early) [1,2][0-9]{3}", (String[] strs) -> {
      assert strs.length == 2;
      String mod = strs[0];
      Integer year = Integer.valueOf(strs[1]);
      return Date.builder().setModifier(mod).setYear(year).build();
    });
    b.addGrammar(
        "[1,2][0-9]{3} (January|February|March|April|May|June|July|August|September|October|November|December)",
        (String[] strs) -> {
          assert strs.length == 2;
          Integer year = Integer.valueOf(strs[0]);
          String month = strs[1];
          return Date.builder().setYear(year).setMonth(month).build();
        });
    b.addGrammar(
        "[1,2][0-9]{3} (Late|Mid|Early) (January|February|March|April|May|June|July|August|September|October|November|December)",
        (String[] strs) -> {
          assert strs.length == 3;
          Integer year = Integer.valueOf(strs[0]);
          String mod = strs[1];
          String month = strs[2];
          return Date.builder().setYear(year).setMonth(month).setModifier(mod).build();
        });
    b.addGrammar(
        "[1,2][0-9]{3} (January|February|March|April|May|June|July|August|September|October|November|December) ([1-9]|1[0-9]|2[0-9]|3[01])",
        (String[] strs) -> {
          assert strs.length == 3;
          Integer year = Integer.valueOf(strs[0]);
          String month = strs[1];
          Integer day = Integer.valueOf(strs[2]);
          return Date.builder().setYear(year).setMonth(month).setDay(day).build();
        });
    b.addGrammar(
        "[1,2][0-9]{3} (January|February|March|April|May|June|July|August|September|October|November|December) Around ([1-9]|1[0-9]|2[0-9]|3[01])",
        (String[] strs) -> {
          assert strs.length == 4;
          Integer year = Integer.valueOf(strs[0]);
          String month = strs[1];
          String modifier = strs[2];
          Integer day = Integer.valueOf(strs[3]);
          return Date
              .builder()
              .setYear(year)
              .setMonth(month)
              .setDay(day)
              .setModifier(modifier)
              .build();
        });
    dateGrammar = b.build();
  }

  private Date() {

  }

  public static Date buildDate(String dateStr) {
    return dateGrammar.parse(dateStr);
  }

  public static Builder builder() {
    return new Builder();
  }

  private static boolean isValidYear(Integer year) {
    if (year < MIN_YEAR || year > MAX_YEAR) {
      return false;
    }
    return true;
  }

  public Integer getYear() {
    return year;
  }

  public String getMonth() {
    return month;
  }

  public Integer getDay() {
    return day;
  }

  public String getModifier() {
    return modifier;
  }

  @Override
  public int compareTo(Date o) {
    assert year != null;
    assert o.year != null;
    // if (year.equals(o.year)) {
    // if (month == null) {
    // if (o.month == null) {
    // if (modifier == null) {
    // if (o.modifier == null) {
    // return 0;
    // } else {
    // return 0;
    // }
    // }
    // }
    // }
    // } else {
    // return year.compareTo(o.year);
    // }
    return 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (year != null && month != null && modifier != null && day != null) {
      sb.append(year);
      sb.append(" ");
      sb.append(month);
      sb.append(" ");
      sb.append(modifier);
      sb.append(" ");
      sb.append(day);
    } else if (year != null && month != null && day != null && modifier == null) {
      sb.append(year);
      sb.append(" ");
      sb.append(month);
      sb.append(" ");
      sb.append(day);
    } else if (year != null && modifier != null && month != null && day == null) {
      sb.append(year);
      sb.append(" ");
      sb.append(modifier);
      sb.append(" ");
      sb.append(month);
    } else if (year != null && modifier == null && month != null && day == null) {
      sb.append(year);
      sb.append(" ");
      sb.append(month);
    } else if (year != null && modifier != null && month == null && day == null) {
      sb.append(modifier);
      sb.append(" ");
      sb.append(year);
      sb.append(" ");
    } else if (year != null && modifier == null && month == null && day == null) {
      sb.append(year);
    }
    return sb.toString();
  }

  public static class Builder {
    private Date date;

    private static List<String> monthsWith30 = ImmutableList
        .of("Janurary", "March", "May", "July", "August", "October", "December");
    private static List<String> monthsWith31 = ImmutableList
        .of("April", "June", "September", "November");

    public Builder() {
      this.date = new Date();
    }

    public Builder setYear(Integer year) {
      if (!isValidYear(year)) {
        throw new IllegalArgumentException("Invalid year.");
      }
      date.year = year;
      return this;
    }

    public Builder setMonth(String month) {
      if (!monthsWith30.contains(month) && !monthsWith31.contains(month)
          && "February".equals(month)) {
        throw new IllegalArgumentException(month + " is not a valid month");
      }
      date.month = month;
      return this;
    }

    public Builder setDay(Integer day) {
      if (day < 0) {
        throw new IllegalArgumentException("Day must be greater than 0");
      }
      if (date.month == null) {
        if (day > 28) {
          throw new IllegalArgumentException(
              "You must set the month before the date to set a day of 29, 30, or 31");
        }
      } else {
        if (date.month.equals("Feburary")) {
          if (day > 29) {
            throw new IllegalArgumentException("Feburary has at most 29 days");
          }
        } else if (monthsWith30.contains(date.month)) {
          if (day > 30) {
            throw new IllegalArgumentException(date.month + " does not have " + day + " days");
          }
        } else if (monthsWith31.contains(date.month)) {
          if (day > 31) {
            throw new IllegalArgumentException(date.month + " does not have " + day + " days");
          }
        }
      }
      date.day = day;
      return this;
    }

    public Builder setModifier(String modifier) {
      if (!"Around".equals(modifier) && !"Early".equals(modifier) && !"Mid".equals(modifier)
          && !"Late".equals(modifier)) {
        throw new IllegalArgumentException(modifier + " is an invalid modifier");
      }
      date.modifier = modifier;
      return this;
    }

    public Date build() {
      return date;
    }
  }
}
