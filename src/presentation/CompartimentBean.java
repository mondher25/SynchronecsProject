package presentation;

 
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped; 
import javax.faces.context.FacesContext;

 

import dao.CompartimentDao;
import dao.PlannerDao;
import entities.Compartiment;
import entities.Planner;


@ManagedBean(name="com")
@ViewScoped
public class CompartimentBean implements Serializable{
	
	@EJB
	private CompartimentDao com;
	
	@EJB
	private PlannerDao plan;
	
	private String name;	
	private String mail ;
 	private Planner planner =new Planner();
 	private Compartiment comp = new Compartiment();
	private Planner selectedPlanner ;
	private Integer idp ;
	private String idCom;
	
 
   
	@PostConstruct
	public void init(){
		System.out.println("compartiment");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");	
		 FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	         fc.getExternalContext().getRequestParameterMap();
	      String idplanner = params.get("idp"); 	      
	      selectedPlanner = plan.getPlannerById(Long.parseLong(idplanner));			    
		
	}
	
	public void addCom(){
	System.out.println("start comp");	 
	  comp.setPlanner(selectedPlanner);
	  com.addCompartiment(comp);
      comp = new Compartiment();
				
		
	}
 
	
 public List<Compartiment> ListeCompByPlanner(){
	 List<Compartiment>	listCom = com.getListCompartimentByPlanner(selectedPlanner.getId());
	 return listCom;
 }
	
	public String getIdPlanner() {
	      FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	         fc.getExternalContext().getRequestParameterMap();
	      return params.get("idp"); 
	}


	// GetterAnd Setter
 
	public String getName() {
		return name;
	}
	public Planner getSelectedPlanner() {
		return selectedPlanner;
	}

	public void setSelectedPlanner(Planner selectedPlanner) {
		this.selectedPlanner = selectedPlanner;
	}

	public String getMail() {
		return mail;
	}
 
	public Planner getPlanner() {
		return planner;
	}
	public void setPlanner(Planner planner) {
		this.planner = planner;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdp() {
		return idp;
	}

	public void setIdp(Integer idp) {
		this.idp = idp;
	}
	public Compartiment getComp() {
		return comp;
	}

	public void setComp(Compartiment comp) {
		this.comp = comp;
	}

	public String getIdCom() {
		return idCom;
	}

	public void setIdCom(String idCom) {
		this.idCom = idCom;
	}

 


 

 
	

}
