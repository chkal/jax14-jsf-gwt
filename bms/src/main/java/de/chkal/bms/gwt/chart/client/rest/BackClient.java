package de.chkal.bms.gwt.chart.client.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import de.chkal.bms.model.Kuchen;

/**
 * REST client
 */
public interface BackClient extends RestService {

  @GET
  @Path("/kuchen")
  public void getArtikel(MethodCallback<List<Kuchen>> callback);

}
