package org.mappingviolence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mappingviolence.poi.date.Date;

import me.colehansen.grammar.Grammar;

public class GrammarTest {

  @Test
  public void TestGrammar() {
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
    Grammar<Date> g = b.build();
    Date d = g.parse("2015 March Around 18");
    assertNotNull(d);
    assertEquals((Integer) 2015, d.getYear());
    assertEquals("March", d.getMonth());
    assertEquals("Around", d.getModifier());
    assertEquals((Integer) 18, d.getDay());
  }

}
