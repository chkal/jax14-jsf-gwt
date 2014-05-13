package de.chkal.bms.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class RezeptZutat implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "kuchen_id")
  @JsonIgnore
  private Kuchen kuchen;

  @ManyToOne
  @JoinColumn(name = "zutat_id")
  private Zutat zutat;

  @Embedded
  private Menge menge;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Kuchen getKuchen() {
    return kuchen;
  }

  public void setKuchen(Kuchen kuchen) {
    this.kuchen = kuchen;
  }

  public Zutat getZutat() {
    return zutat;
  }

  public void setZutat(Zutat zutat) {
    this.zutat = zutat;
  }

  public Menge getMenge() {
    return menge;
  }

  public void setMenge(Menge menge) {
    this.menge = menge;
  }

}
