package de.chkal.bms.gwt.chart.client.d3;

import com.google.gwt.core.client.JavaScriptObject;

import de.chkal.bms.model.Kuchen;

/**
 * Artikel in einem D3 Chart
 */
public class D3Artikel extends JavaScriptObject {

  protected D3Artikel() {
    // required for JavaScriptObject
  }

  public final void init(Kuchen kuchen, double kostenNeu) {

    setName(kuchen.getName());
    setKostenAlt(kuchen.getKosten());
    setKostenNeu(kostenNeu);

    double differenz = kostenNeu - kuchen.getKosten();
    setDifferenz(differenz);
    setRelativ(100 * differenz / kuchen.getKosten());

  }

  private final native String setName(String name) /*-{
    this.name = name;
  }-*/;

  private final native String setKostenAlt(Double kostenAlt) /*-{
    this.kostenAlt = kostenAlt;
  }-*/;

  private final native String setKostenNeu(Double kostenNeu) /*-{
    this.kostenNeu = kostenNeu;
  }-*/;

  private final native String setDifferenz(Double differenz) /*-{
    this.differenz = Math.round(differenz * 100) / 100;
  }-*/;

  private final native String setRelativ(Double relativ) /*-{
    this.relativ = Math.round(relativ * 10) / 10;
  }-*/;

}
