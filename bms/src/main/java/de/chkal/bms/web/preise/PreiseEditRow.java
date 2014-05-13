package de.chkal.bms.web.preise;

import de.chkal.bms.model.Menge;
import de.chkal.bms.model.Zutat;

public class PreiseEditRow {

  private Zutat zutat;

  private Double preisNeu;

  public PreiseEditRow(Zutat zutat) {
    this.zutat = zutat;
    this.preisNeu = zutat.getPreis();
  }

  public String getName() {
    return zutat.getName();
  }

  public Menge getMenge() {
    return zutat.getMenge();
  }

  public Double getPreisAlt() {
    return zutat.getPreis();
  }

  public Double getPreisNeu() {
    return preisNeu;
  }

  public void setPreisNeu(Double preisNeu) {
    this.preisNeu = preisNeu;
  }

  public Long getId() {
    return zutat.getId();
  }

}
