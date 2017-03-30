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

import dao.CompteDao;
 
import dao.PlannerDao;
import dao.UserDao;
import entities.Compte;
import entities.Planner;
import entities.User;


@ManagedBean(name="plan")
@SessionScoped
public class PlanBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PlannerDao plannerDao;
	
	@EJB
	private UserDao userDao;
	
 
	
	private String nom;	
	private String mail ;
	private String grade;
 
 
	
	private Planner planner =new Planner();
	private User compteUser=new User();
	private User connectedUser;
	
	
	

	@PostConstruct
	public void Init(){
		System.out.println(" -- - --- --- -planBean- --- --- ");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		grade = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grade");	
		nom = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nom");	
		 
		connectedUser=userDao.getUserByMailId(mail);
		System.out.println("init : id user connecte ="+ mail);
		System.out.println("init : id user connecte ="+ grade);
	
	}
	

	public void addNewPlanner(){
	 System.out.println("start add Planner");
		
	 	planner.setUser(connectedUser);
		planner.setUserGrade(connectedUser.getGrade());	
		plannerDao.AddPlanner(planner); 
		
		planner=new Planner();
		
		System.out.println("end add Planner");
		 
		System.out.println("id user connecte ="+ mail);
	}
	
	public List<Planner> listePlannerByIdCompte(){
		List<Planner> listPlannerCompte = new ArrayList<Planner>();
		listPlannerCompte = plannerDao.getAllPlannerByMailId(mail);
		System.out.println(" --- -- -- end Liste  Planner --- ");
		return listPlannerCompte ;
		
	}
	
//	public List<Planner> listePlanner(){
//		List<Planner> list = new ArrayList<Planner>();
//		list = plannerDao.getAllPlanner();
//		System.out.println(" --- -- -- end Liste  Planner --- ");
//		return list ;
//		
//	}
//	
//	   public void onRowSelect(SelectEvent event) throws IOException {
//		   FacesContext context = FacesContext.getCurrentInstance();	
//		   HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
//		   context.getExternalContext().redirect(req.getContextPath()+"/faces/planner/tache.xhtml");
//	    
//	    }
	
	
	// GetterAnd Setter


 
 

	public String getMail() {
		return mail;
	}

 
	public Planner getPlanner() {
		return planner;
	}
	public void setPlanner(Planner planner) {
		this.planner = planner;
	}
 
 	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


 

 

 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

 

 


	public User getCompteUser() {
		return compteUser;
	}


	public void setCompteUser(User compteUser) {
		this.compteUser = compteUser;
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public User getConnectedUser() {
		return connectedUser;
	}


	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}


	public PlannerDao getPlannerDao() {
		return plannerDao;
	}

	public void setPlannerDao(PlannerDao plannerDao) {
		this.plannerDao = plannerDao;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


 

 

 
	

}
