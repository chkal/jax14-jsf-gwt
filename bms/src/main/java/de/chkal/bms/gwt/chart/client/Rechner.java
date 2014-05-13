package de.chkal.bms.gwt.chart.client;

import java.util.List;

import de.chkal.bms.gwt.chart.client.d3.D3Chart;
import de.chkal.bms.model.Kuchen;
import de.chkal.bms.model.RezeptZutat;
import de.chkal.bms.model.Zutat;
import de.chkal.bms.rechner.RezeptRechner;

public class Rechner implements PreisListener {

  private List<Kuchen> artikelList;

  private D3Chart chart;

  public Rechner(List<Kuchen> artikelList, D3Chart chart) {
    this.artikelList = artikelList;
    this.chart = chart;
  }

  @Override
  public void onPreisChanged(long zutatId, Double preis) {

    chart.reset();

    for (Kuchen artikel : artikelList) {

      // Preis der Zutat aktualisieren
      updateZutatPreis(artikel, zutatId, preis);

      // Herstellungskosten berechnen
      RezeptRechner rechner = new RezeptRechner();
      for (RezeptZutat rezeptZutat : artikel.getZutaten()) {
        rechner.add(rezeptZutat);
      }
      Double kosten = rechner.getSumme();

      // Chart aktualisieren
      chart.addArtikel(artikel, kosten);

    }

    chart.redraw();

  }

  /**
   * Aktualisiert die Preise der Rezeptzutaten
   */
  private void updateZutatPreis(Kuchen artikel, long zutatId, Double preis) {

    for (RezeptZutat rezeptZutat : artikel.getZutaten()) {

      Zutat zutat = rezeptZutat.getZutat();
      if (zutat.getId() == zutatId) {
        zutat.setPreis(preis);
      }

    }

  }

}
