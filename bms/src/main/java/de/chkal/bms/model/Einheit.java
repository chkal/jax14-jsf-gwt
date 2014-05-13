package de.chkal.bms.model;

public enum Einheit {

  G("g", 1, null),
  EL("EL", 10, G),
  KG("kg", 1000, G),

  ML("ml", 1, null),
  DL("dl", 500, ML),
  L("l", 1000, ML),
  
  ST("St", 1, null);

  private final String label;
  private final int faktor;
  private final Einheit basis;

  private Einheit(String label, int faktor, Einheit basis) {
    this.label = label;
    this.faktor = faktor;
    this.basis = basis;
  }

  public String getLabel() {
    return label;
  }

  public int getFaktor() {
    return faktor;
  }

  public Einheit getBasiseinheit() {
    return basis != null ? basis : this;
  }

}
