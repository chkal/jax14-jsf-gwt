package de.chkal.bms.model;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Menge {

  @Basic
  private int wert;

  @Basic
  private Einheit einheit;

  public Menge() {
  }
  
  public Menge(int wert, Einheit einheit) {
    this.wert = wert;
    this.einheit = einheit;
  }
  
  public int getWert() {
    return wert;
  }

  public void setWert(int menge) {
    this.wert = menge;
  }

  public Einheit getEinheit() {
    return einheit;
  }

  public void setEinheit(Einheit einheit) {
    this.einheit = einheit;
  }

}
