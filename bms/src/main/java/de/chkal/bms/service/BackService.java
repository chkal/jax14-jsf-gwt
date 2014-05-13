package de.chkal.bms.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.chkal.bms.model.Kuchen;
import de.chkal.bms.model.RezeptZutat;
import de.chkal.bms.model.Zutat;
import de.chkal.bms.rechner.RezeptRechner;

@Stateless
public class BackService {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Zutat> listZutaten() {
    return entityManager.createQuery("SELECT z FROM Zutat z ORDER BY z.name", Zutat.class)
        .getResultList();
  }

  public List<Kuchen> listKuchen() {
    return entityManager.createQuery(
        "SELECT DISTINCT k FROM Kuchen k LEFT JOIN FETCH k.zutaten ORDER BY k.name", Kuchen.class)
        .getResultList();
  }

  public int updatePreis(Long zutatId, Double preisNeu) {

    Zutat zutat = entityManager.find(Zutat.class, zutatId);
    zutat.setPreis(preisNeu);

    List<Kuchen> betroffeneArtikel = findKuchenByZutat(zutat);
    for (Kuchen kuchen : betroffeneArtikel) {

      RezeptRechner rechner = new RezeptRechner();
      for (RezeptZutat rezeptZutat : kuchen.getZutaten()) {
        rechner.add(rezeptZutat);
      }
      kuchen.setKosten(rechner.getSumme());

    }

    return betroffeneArtikel.size();

  }

  private List<Kuchen> findKuchenByZutat(Zutat zutat) {
    return entityManager
        .createQuery("SELECT DISTINCT rz.kuchen FROM RezeptZutat rz WHERE rz.zutat = :zutat",
            Kuchen.class)
        .setParameter("zutat", zutat)
        .getResultList();
  }

}
