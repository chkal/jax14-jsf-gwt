package de.chkal.bms.rechner;

import de.chkal.bms.model.Einheit;
import de.chkal.bms.model.Menge;
import de.chkal.bms.model.RezeptZutat;
import de.chkal.bms.model.Zutat;

public class RezeptRechner {

  private Double summe = 0.0;

  public void add(RezeptZutat rezeptZutat) {
    add(rezeptZutat.getMenge(), rezeptZutat.getZutat());
  }

  public void add(Menge menge, Zutat zutat) {
    add(menge.getWert(), menge.getEinheit(), zutat);
  }

  public void add(int menge, Einheit einheit, Zutat zutat) {

    if (zutat.getMenge().getEinheit().getBasiseinheit() != einheit.getBasiseinheit()) {
      throw new IllegalArgumentException("Unterschiedliche Basiseinheiten bei Zutat: " +
          zutat.getName());
    }

    double preisProBasiseinheit = getPreisProBasiseinheit(zutat);
    int mengeInBasiseinheit = menge * einheit.getFaktor();

    summe += Math.max(0, mengeInBasiseinheit * preisProBasiseinheit);

  }

  private double getPreisProBasiseinheit(Zutat zutat) {
    return zutat.getPreis() /
        (zutat.getMenge().getEinheit().getFaktor() * zutat.getMenge().getWert());
  }

  private double round(double value) {
    return ((int) (value * 100)) / 100.0;
  }

  public Double getSumme() {
    return round(summe);
  }

}
