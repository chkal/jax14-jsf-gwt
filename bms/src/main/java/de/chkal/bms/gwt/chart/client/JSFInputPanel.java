package de.chkal.bms.gwt.chart.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Kapselt die JSF Input Komponenten
 */
public class JSFInputPanel implements ChangeHandler {

  private PreisListener preisListener;

  /**
   * Konstruktor
   */
  public JSFInputPanel() {

    JsArray<Element> inputs = getZutatInputs();

    for (int i = 0; i < inputs.length(); i++) {

      Element element = inputs.get(i);

      TextBox textBox = TextBox.wrap(element);

      textBox.addChangeHandler(this);

    }

  }

  @Override
  public void onChange(ChangeEvent event) {

    TextBox textbox = (TextBox) event.getSource();

    String id = textbox.getElement().getAttribute("data-zutat-id");
    double preis = parsePreis(textbox.getValue());

    preisListener.onPreisChanged(Long.valueOf(id), preis);

  }

  private native JsArray<Element> getZutatInputs()/*-{

    return $wnd.jQuery('input[data-zutat-id]').get();

  }-*/;

  private double parsePreis(String value) {
    return Double.valueOf(value.replace(",", "."));
  }

  public PreisListener getPreisListener() {
    return preisListener;
  }

  public void setPreisListener(PreisListener listener) {
    this.preisListener = listener;
  }

}
