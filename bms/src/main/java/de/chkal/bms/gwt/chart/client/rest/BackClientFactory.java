package de.chkal.bms.gwt.chart.client.rest;

import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;

/**
 * Erzeugt {@link BackClient} Objekte
 */
public class BackClientFactory {

  public static BackClient create() {

    Resource resource = new Resource(GWT.getModuleBaseURL() + "../rest");

    BackClient client = GWT.create(BackClient.class);
    ((RestServiceProxy) client).setResource(resource);
    return client;

  }

}
