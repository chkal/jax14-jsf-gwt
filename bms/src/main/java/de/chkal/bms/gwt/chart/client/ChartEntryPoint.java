package de.chkal.bms.gwt.chart.client;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

import de.chkal.bms.gwt.chart.client.d3.D3Chart;
import de.chkal.bms.gwt.chart.client.rest.BackClient;
import de.chkal.bms.gwt.chart.client.rest.BackClientFactory;
import de.chkal.bms.model.Kuchen;

/**
 * Einstiegspunkt für das GWT Module
 */
public class ChartEntryPoint implements EntryPoint {

  @Override
  public void onModuleLoad() {

    // RestyGWT Client
    BackClient client = BackClientFactory.create();

    // List der Artikel abrufen
    client.getArtikel(new MethodCallback<List<Kuchen>>() {

      @Override
      public void onSuccess(Method method, List<Kuchen> response) {
        initializePage(response);
      }

      @Override
      public void onFailure(Method method, Throwable exception) {
        Window.alert("Failed: " + exception.getMessage());
      }

    });

  }

  protected void initializePage(List<Kuchen> artikelList) {

    // Chart
    D3Chart chart = new D3Chart("chart");

    // Koordinator
    Rechner rechner = new Rechner(artikelList, chart);

    // JSF Inputs
    JSFInputPanel panel = new JSFInputPanel();
    panel.setPreisListener(rechner);

    // Chart 1x zeichnen
    for (Kuchen kuchen : artikelList) {
      chart.addArtikel(kuchen, kuchen.getKosten());
    }
    chart.redraw();

  }

}
