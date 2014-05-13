package de.chkal.bms.calc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.chkal.bms.model.Einheit;
import de.chkal.bms.model.Menge;
import de.chkal.bms.model.Zutat;
import de.chkal.bms.rechner.RezeptRechner;

public class RezeptRechnerTest {

  private final static double DELTA = 0.00000000000001;

  private final RezeptRechner rechner = new RezeptRechner();

  private Zutat eier;
  private Zutat zucker;
  private Zutat mehl;

  @Before
  public void before() {

    zucker = new Zutat();
    zucker.setName("Zucker");
    zucker.setMenge(new Menge(1, Einheit.KG));
    zucker.setPreis(4.00);

    eier = new Zutat();
    eier.setName("Eier");
    eier.setMenge(new Menge(10, Einheit.ST));
    eier.setPreis(5.0);

    mehl = new Zutat();
    mehl.setName("Mehr");
    mehl.setMenge(new Menge(3, Einheit.KG));
    mehl.setPreis(1.0);

  }

  @Test
  public void sumIsZeroByDefault() {
    assertEquals(0.0, rechner.getSumme(), DELTA);
  }

  @Test
  public void addsMultipleValues() {
    rechner.add(10, Einheit.ST, eier);
    rechner.add(1, Einheit.KG, zucker);
    assertEquals(9.0, rechner.getSumme(), DELTA);
  }

  @Test
  public void sameUnitAndAmount() {
    rechner.add(10, Einheit.ST, eier);
    assertEquals(5.0, rechner.getSumme(), DELTA);
  }

  @Test
  public void sameUnitSmallerAmount() {
    rechner.add(2, Einheit.ST, eier);
    assertEquals(1.0, rechner.getSumme(), DELTA);
  }

  @Test
  public void sameUnitLargerAmount() {
    rechner.add(20, Einheit.ST, eier);
    assertEquals(10.0, rechner.getSumme(), DELTA);
  }

  @Test
  public void smallerUnit() {
    rechner.add(500, Einheit.G, zucker);
    assertEquals(2.0, rechner.getSumme(), DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void differentBaseUnit() {
    rechner.add(1, Einheit.L, zucker);
  }

  @Test
  public void rounding() {
    rechner.add(1, Einheit.KG, mehl);
    assertEquals(0.33, rechner.getSumme(), DELTA);
  }

}
