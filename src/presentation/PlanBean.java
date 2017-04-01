package presentation;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
import javax.faces.context.FacesContext;

 

import dao.PlannerDao;
import dao.UserDao;
import dao.AffectationPlannerUserDao;
import entities.Planner;
import entities.User;
import entities.AffectationPlannerUser;


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
	
	@EJB
	private AffectationPlannerUserDao affectationPlannerUserDao;

	private List<User> listeUser =new ArrayList<>();
	
	private List<User> finalListUserObject =new ArrayList<>();
	private List<String> finalListUserString =new ArrayList<>();
 
	private String nom;	
	private String mail ;
	private String grade;
	private boolean etat;
	private String nomSociete;

	private AffectationPlannerUser affectationPlannerUser=new AffectationPlannerUser();
	
	private Planner planner =new Planner();
	private User compteUser=new User();
	private User connectedUser;
	
	Iterator<User> it=finalListUserObject.listIterator();
	 
	

	
	

	@PostConstruct
	public void Init(){
		System.out.println(" -- - --- --- -planBean- --- --- ");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		grade = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grade");	
		nom = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nom");	
		nomSociete=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nomSociete");
		connectedUser=userDao.getUserByMailId(mail);
		System.out.println("init : id user connecte ="+ mail);
		System.out.println("init : id user connecte ="+ grade);
	
	}
	
	//////AUTO
    public List<User> completeTheme(String query) {
        List<User> allUsers = userDao.getAllUser();
        List<User> filteredUsers = new ArrayList<User>();
        
        for (int i = 0; i < allUsers.size(); i++) {
        	User skin = allUsers.get(i);
            if(skin.toString().toLowerCase().startsWith(query)) {
            	filteredUsers.add(skin);
            }
        }
         
         
        return filteredUsers;
       
    }
    
  
	

	public void addNewPlanner(){
	 System.out.println("start add Planner");	
	 	planner.setUser(connectedUser);
		planner.setUserGrade(connectedUser.getGrade());	
		planner.setNomSociete(nomSociete);
		
	
		
		for(User u :finalListUserObject){
		    AffectationPlannerUser affectationPlannerUser=new AffectationPlannerUser();
		    affectationPlannerUser.setPlanner(planner);
		    
 
		    affectationPlannerUser.setUser(u);
		    affectationPlannerUserDao.addAff(affectationPlannerUser);
		}
		plannerDao.AddPlanner(planner); 
		
		planner=new Planner();
		
		System.out.println("end add Planner");
		 
		System.out.println("id user connecte ="+ mail);
		
	}
	
	public List<Planner> listePlannerByIdCompte(){
		List<Planner> listPlannerCompte = new ArrayList<Planner>();
		listPlannerCompte = plannerDao.getAllPlannerByMailAndnomSociete(mail, nomSociete);
		checketat();
		System.out.println(" --- -- -- end Liste  Planner --- ");
		return listPlannerCompte ;
		
	}
	
	public void checketat(){
		if (etat == true)
		{ 
			System.out.println("ok True");
		}else
		{
			System.out.println("Not ok");
		}
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


	public boolean isEtat() {
		return etat;
	}


	public void setEtat(boolean etat) {
		this.etat = etat;
	}


	public String getNomSociete() {
		return nomSociete;
	}


	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public List<User> getListeUser() {
		return listeUser;
	}

	public void setListeUser(List<User> listeUser) {
		this.listeUser = listeUser;
	}

	public List<User> getFinalListUserObject() {
		return finalListUserObject;
	}

	public void setFinalListUserObject(List<User> finalListUserObject) {
		this.finalListUserObject = finalListUserObject;
	}

	public List<String> getFinalListUserString() {
		return finalListUserString;
	}

	public void setFinalListUserString(List<String> finalListUserString) {
		this.finalListUserString = finalListUserString;
	}

	public AffectationPlannerUserDao getAffectationPlannerUserDao() {
		return affectationPlannerUserDao;
	}

	public void setAffectationPlannerUserDao(AffectationPlannerUserDao affectationPlannerUserDao) {
		this.affectationPlannerUserDao = affectationPlannerUserDao;
	}

	public AffectationPlannerUser getAffectationPlannerUser() {
		return affectationPlannerUser;
	}

	public void setAffectationPlannerUser(AffectationPlannerUser affectationPlannerUser) {
		this.affectationPlannerUser = affectationPlannerUser;
	}

 

 

 

 
	

}
