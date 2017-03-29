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
import dao.GroupeDao;
import dao.PlannerDao;
import dao.UserDao;
import entities.Compte;
import entities.Groupe;
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
	private CompteDao compteDao;
	
	@EJB
	private GroupeDao groupeDao;
	
	@EJB
	private UserDao userDao;
	
	private String name;	
	private String mail ;
	private String type;
	private String idUser;
	
	private Planner planner =new Planner();
	private Compte compteUser=new Compte();
	private User connectedUser;
	private Groupe groupe=new Groupe();
	
	
	

	@PostConstruct
	public void Init(){
		System.out.println(" -- - --- --- -planBean- --- --- ");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");	
		idUser=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUser");
		connectedUser=userDao.getUserById(Long.parseLong(idUser));
		System.out.println("init : id user connecte ="+ Long.parseLong(idUser));
		 
	
	
	}
	
	

	public void addNewPlanner(){
	 System.out.println("start add Planner");
		
		
		planner.setUser(connectedUser);		
		plannerDao.AddPlanner(planner); 
		planner=new Planner();
		
		System.out.println("end add Planner");
		 
		System.out.println("id user connecte ="+ Long.parseLong(idUser));
	}
	
	public List<Planner> listePlannerByIdCompte(){
		List<Planner> listPlannerCompte = new ArrayList<Planner>();
		listPlannerCompte = plannerDao.getAllPlannerById(Long.parseLong(idUser));
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

 

	public CompteDao getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Compte getCompteUser() {
		return compteUser;
	}

	public void setCompteUser(Compte compteUser) {
		this.compteUser = compteUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

 
	public PlannerDao getPlannerDao() {
		return plannerDao;
	}

	public void setPlannerDao(PlannerDao plannerDao) {
		this.plannerDao = plannerDao;
	}



	public GroupeDao getGroupeDao() {
		return groupeDao;
	}



	public void setGroupeDao(GroupeDao groupeDao) {
		this.groupeDao = groupeDao;
	}



	public UserDao getUserDao() {
		return userDao;
	}



	public Groupe getGroupe() {
		return groupe;
	}



	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
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

 

 
	

}
