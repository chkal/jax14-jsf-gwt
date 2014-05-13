package de.chkal.bms.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Zutat implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Basic
  private String name;

  @Embedded
  private Menge menge;

  @Basic
  private Double preis;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPreis() {
    return preis;
  }

  public void setPreis(Double preis) {
    this.preis = preis;
  }

  public Menge getMenge() {
    return menge;
  }

  public void setMenge(Menge menge) {
    this.menge = menge;
  }

}
