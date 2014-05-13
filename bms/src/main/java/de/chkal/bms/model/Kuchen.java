package de.chkal.bms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kuchen implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @OneToMany(mappedBy = "kuchen")
  private List<RezeptZutat> zutaten = new ArrayList<>();
  
  private Double kosten;
  
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

  public List<RezeptZutat> getZutaten() {
    return zutaten;
  }

  public void setZutaten(List<RezeptZutat> zutaten) {
    this.zutaten = zutaten;
  }

  public Double getKosten() {
    return kosten;
  }

  public void setKosten(Double kosten) {
    this.kosten = kosten;
  }

  public Double getPreis() {
    return preis;
  }

  public void setPreis(Double preis) {
    this.preis = preis;
  }

}
