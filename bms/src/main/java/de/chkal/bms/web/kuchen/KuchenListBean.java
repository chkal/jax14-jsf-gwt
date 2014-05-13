package de.chkal.bms.web.kuchen;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.chkal.bms.model.Kuchen;
import de.chkal.bms.service.BackService;

@Named
@RequestScoped
public class KuchenListBean {

  @EJB
  private BackService backService;

  private List<Kuchen> kuchen;

  @PostConstruct
  public void init() {
    kuchen = backService.listKuchen();
  }

  public List<Kuchen> getKuchen() {
    return kuchen;
  }

}
