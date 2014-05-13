package de.chkal.bms.db;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.chkal.bms.model.Einheit;
import de.chkal.bms.model.Kuchen;
import de.chkal.bms.model.Menge;
import de.chkal.bms.model.RezeptZutat;
import de.chkal.bms.model.Zutat;
import de.chkal.bms.rechner.RezeptRechner;

@Startup
@Singleton
public class DataCreator {

  @PersistenceContext
  private EntityManager entityManager;

  @PostConstruct
  public void init() {
    resetDatabase();
    createTestData();
  }

  private void createTestData() {

    Zutat zucker = createZutat("Zucker", 1.29, 1, Einheit.KG);
    Zutat butter = createZutat("Butter", 1.79, 250, Einheit.G);
    Zutat milch = createZutat("Milch", 0.99, 1, Einheit.L);
    Zutat backpulver = createZutat("Backpulver", 1.09, 10, Einheit.ST);
    Zutat eier = createZutat("Eier", 1.89, 6, Einheit.ST);
    Zutat mehl = createZutat("Mehl", 1.29, 1, Einheit.KG);
    Zutat kirschen = createZutat("Kirschen", 3.99, 720, Einheit.G);
    Zutat vanillezucker = createZutat("Vanillezucker", 0.99, 1, Einheit.ST);
    Zutat kakao = createZutat("Kakaopulver", 2.99, 250, Einheit.G);
    Zutat quark = createZutat("Quark", 0.49, 250, Einheit.G);

    // http://www.chefkoch.de/rezepte/1075171213201032/Omas-Marmorkuchen.html
    Kuchen donauwelle = createKuchen("Mamorkuchen");
    addRezeptZutat(donauwelle, butter, 125, Einheit.G);
    addRezeptZutat(donauwelle, zucker, 120, Einheit.G);
    addRezeptZutat(donauwelle, vanillezucker, 1, Einheit.ST);
    addRezeptZutat(donauwelle, eier, 2, Einheit.ST);
    addRezeptZutat(donauwelle, mehl, 250, Einheit.G);
    addRezeptZutat(donauwelle, backpulver, 1, Einheit.ST);
    addRezeptZutat(donauwelle, milch, 100, Einheit.ML);
    addRezeptZutat(donauwelle, kakao, 3, Einheit.EL);
    updatePreis(donauwelle);

    // http://www.chefkoch.de/rezepte/2444671385284674/Einfacher-Kirschkuchen-nach-dem-Rezept-meiner-Oma.html
    Kuchen kirschkuchen = createKuchen("Kirschkuchen");
    addRezeptZutat(kirschkuchen, butter, 100, Einheit.G);
    addRezeptZutat(kirschkuchen, zucker, 150, Einheit.G);
    addRezeptZutat(kirschkuchen, eier, 3, Einheit.ST);
    addRezeptZutat(kirschkuchen, vanillezucker, 1, Einheit.ST);
    addRezeptZutat(kirschkuchen, mehl, 150, Einheit.G);
    addRezeptZutat(kirschkuchen, backpulver, 1, Einheit.ST);
    addRezeptZutat(kirschkuchen, kirschen, 500, Einheit.G);
    updatePreis(kirschkuchen);

    // http://www.chefkoch.de/rezepte/463581139481068/Einfacher-Quarkkuchen-ohne-Boden.html
    Kuchen quarkkuchen = createKuchen("Quarkkuchen");
    addRezeptZutat(quarkkuchen, butter, 125, Einheit.G);
    addRezeptZutat(quarkkuchen, zucker, 400, Einheit.G);
    addRezeptZutat(quarkkuchen, eier, 4, Einheit.ST);
    addRezeptZutat(quarkkuchen, backpulver, 1, Einheit.ST);
    addRezeptZutat(quarkkuchen, quark, 1, Einheit.KG);
    addRezeptZutat(quarkkuchen, vanillezucker, 2, Einheit.ST);
    updatePreis(quarkkuchen);

  }

  private void updatePreis(Kuchen donauwelle) {
    RezeptRechner rezeptRechner = new RezeptRechner();
    for (RezeptZutat rezeptZutat : donauwelle.getZutaten()) {
      rezeptRechner.add(rezeptZutat);
    }
    donauwelle.setKosten(rezeptRechner.getSumme());
    donauwelle.setPreis(rezeptRechner.getSumme() + 3);
  }

  private RezeptZutat addRezeptZutat(Kuchen kuchen, Zutat zutat, int menge, Einheit einheit) {
    RezeptZutat rz = new RezeptZutat();
    rz.setMenge(new Menge(menge, einheit));
    rz.setZutat(zutat);
    rz.setKuchen(kuchen);
    kuchen.getZutaten().add(rz);
    entityManager.persist(rz);
    return rz;
  }

  private Kuchen createKuchen(String name) {
    Kuchen kuchen = new Kuchen();
    kuchen.setName(name);
    kuchen.setKosten(0.0);
    kuchen.setPreis(0.0);
    entityManager.persist(kuchen);
    return kuchen;
  }

  private Zutat createZutat(String name, double preis, int menge, Einheit einheit) {
    Zutat zutat = new Zutat();
    zutat.setName(name);
    zutat.setPreis(preis);
    zutat.setMenge(new Menge(menge, einheit));
    entityManager.persist(zutat);
    return zutat;
  }

  private void resetDatabase() {
    entityManager.createQuery("DELETE FROM RezeptZutat");
    entityManager.createQuery("DELETE FROM Kuchen");
    entityManager.createQuery("DELETE FROM Zutat");
  }

}
