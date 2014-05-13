package de.chkal.bms.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.chkal.bms.model.Kuchen;
import de.chkal.bms.service.BackService;

@Path("/")
public class BackResource {

  @EJB
  private BackService backService;

  @GET
  @Path("/kuchen")
  @Produces("application/json")
  public List<Kuchen> listKuchen() {
    return backService.listKuchen();
  }

}
