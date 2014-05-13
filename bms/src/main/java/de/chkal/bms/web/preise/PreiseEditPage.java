package de.chkal.bms.web.preise;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.chkal.bms.model.Zutat;
import de.chkal.bms.service.BackService;

@Named
@RequestScoped
public class PreiseEditPage {

  @EJB
  private BackService backService;

  private final List<PreiseEditRow> rows = new ArrayList<>();

  @PostConstruct
  public void init() {
    for (Zutat zutat : backService.listZutaten()) {
      rows.add(new PreiseEditRow(zutat));
    }
  }
  
  public String speichern() {
    
    for (PreiseEditRow row : rows) {
      backService.updatePreis(row.getId(), row.getPreisNeu());
    }
    
    FacesContext.getCurrentInstance().addMessage(null, 
        new FacesMessage("Die Herstellungskosten wurden neu berechnet."));
    return null;
  }

  public List<PreiseEditRow> getRows() {
    return rows;
  }

}
