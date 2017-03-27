package presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import dao.PlannerDao;
import entities.Planner;


@ManagedBean(name="plan")
@SessionScoped
public class PlanBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PlannerDao plan;
	
	private String name;	
	private String mail ;
	private String type;	
	
	private Planner planner =new Planner();
	
	
	
 

	@PostConstruct
	public void Init(){
		System.out.println("planBean");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");	
		
	}

	public void addNewPlanner(){
	 System.out.println("start add");
		plan.AddPlanner(name, type); 
		System.out.println("end add");
		
	}
	
	public List<Planner> listePlanner(){
		List<Planner> list = new ArrayList<Planner>();
		list = plan.getAllPlanner();
		return list ;
		
	}
//	   public void onRowSelect(SelectEvent event) throws IOException {
//		   FacesContext context = FacesContext.getCurrentInstance();	
//		   HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
//		   context.getExternalContext().redirect(req.getContextPath()+"/faces/planner/tache.xhtml");
//	    
//	    }
	
	
	// GetterAnd Setter


 
	public String getName() {
		return name;
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

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

 

 
	

}
