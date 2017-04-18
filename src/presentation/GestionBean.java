package presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CompartimentAffPlannerUserDao;
import dao.CompartimentDao;
import dao.TacheDao;
import dao.TacheUPCDao;
import dao.UserDao;
import entities.Compartiment;
import entities.Tache;
import entities.TacheUPC;
import entities.User;

@ManagedBean(name="gestionBean")
@ViewScoped
public class GestionBean implements Serializable {
	
	
	
	@EJB
	private TacheDao tacheDao;
	
	@EJB
	private UserDao userDao;
	
	@EJB
	private TacheUPCDao tacheUPCDao;
	
	@EJB
	private CompartimentDao compartimentDao;
	
	@EJB
	private CompartimentAffPlannerUserDao compartimentAffPlannerUserDao;
	
	private User connectedUser;
	private Tache tache;
	private Tache deletedTache;
	private Compartiment deletedCompartiment;
	private TacheUPC tacheUPC;
	private Tache delTache;
	private User logedUser=new User();
	
	private String mail;
	private String grade;	
	private String nom;
	private Long id;
	
	
	@PostConstruct
	public void init(){
		
		System.out.println(" -- - --- --- -Gestion Tache Start- --- --- ");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser");
 
		 
	 
 
		
	}
	

	public List<Tache> listeAllTache()
	{
		List<Tache> listeTache=new ArrayList<>();
		listeTache=tacheDao.getAllTache(logedUser.getId());		
		return listeTache;
	}
	public List<Compartiment> listeAllCompartiment(){
		List<Compartiment> listeCompartiment=new ArrayList<>();
		listeCompartiment=compartimentDao.getAllCompartiment(logedUser.getId());
		return listeCompartiment;
	}
	
    public void supprimer() {
    	System.out.println("----start supprimer Tache-----");
    	tacheDao.remove(deletedTache);
    	System.out.println("end supprimer Tache");
    	 
        System.out.println("--- start supprimer TacheUPC ----- ");
    	   tacheUPCDao.delete( deletedTache.getId()); 
    	System.out.println("end supprimer TacheUPC");
	


        
    }
    
    public void supprimerCom(){
    	System.out.println("----start supprimer Compartiment-----");
    	compartimentDao.removeCom(deletedCompartiment);
    	System.out.println("end supprimer Compartiment");
        System.out.println("--- start supprimer CompartimentAffUserPlanner----- ");
    	  compartimentAffPlannerUserDao.deletComp(deletedCompartiment.getId());
    	System.out.println("end supprimer CompartimentAffUserPlanner");
    	
    	
    	
    }
     

	//Getter And Setter---------------------------------------------->	
	
	public TacheDao getTacheDao() {
		return tacheDao;
	}

	public void setTacheDao(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	public Tache getDeletedTache() {
		return deletedTache;
	}
	public void setDeletedTache(Tache deletedTache) {
		this.deletedTache = deletedTache;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public TacheUPCDao getTacheUPCDao() {
		return tacheUPCDao;
	}


	public void setTacheUPCDao(TacheUPCDao tacheUPCDao) {
		this.tacheUPCDao = tacheUPCDao;
	}


	public TacheUPC getTacheUPC() {
		return tacheUPC;
	}


	public void setTacheUPC(TacheUPC tacheUPC) {
		this.tacheUPC = tacheUPC;
	}


	public Tache getDelTache() {
		return delTache;
	}


	public void setDelTache(Tache delTache) {
		this.delTache = delTache;
	}


	public User getLogedUser() {
		return logedUser;
	}


	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}


	public CompartimentDao getCompartimentDao() {
		return compartimentDao;
	}


	public void setCompartimentDao(CompartimentDao compartimentDao) {
		this.compartimentDao = compartimentDao;
	}


	public Compartiment getDeletedCompartiment() {
		return deletedCompartiment;
	}


	public void setDeletedCompartiment(Compartiment deletedCompartiment) {
		this.deletedCompartiment = deletedCompartiment;
	}

 
	
	
	

}
