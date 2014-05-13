package de.chkal.bms.web.zutaten;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.chkal.bms.model.Zutat;
import de.chkal.bms.service.BackService;

@Named
@RequestScoped
public class ZutatenListBean {

  @EJB
  private BackService backService;

  private List<Zutat> zutaten;

  @PostConstruct
  public void init() {
    zutaten = backService.listZutaten();
  }

  public List<Zutat> getZutaten() {
    return zutaten;
  }

}
