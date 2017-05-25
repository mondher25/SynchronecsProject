package presentation;

import java.io.Serializable;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
 
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import dao.AffectationPlannerUserDao;
import dao.CommentDao;
import dao.CompartimentAffPlannerUserDao;
import dao.CompartimentDao;
import dao.PlannerDao;
import dao.SupPlannerDao;
import dao.TacheDao;
import dao.TacheUPCDao;
import dao.UserDao;
import entities.Compartiment;
import entities.Planner;
import entities.Tache;
import entities.TacheUPC;
import entities.User;

@ManagedBean(name="gestionBean")
@SessionScoped
public class GestionBean implements Serializable {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@EJB
	private PlannerDao plannerDao;
	
	@EJB
	private AffectationPlannerUserDao affectationPlannerUserDao;
	
	@EJB
	private CommentDao commentDao;
	
	@EJB
	private SupPlannerDao supPlannerDao;
	
	
	private User connectedUser;
 
	private Tache deletedTache;
	private Compartiment deletedCompartiment;
	private Planner deletedPlanner;
	
	private TacheUPC tacheUPC;
	private Tache delTache;
	private User logedUser=new User();
	
	private Compartiment compartiment=new Compartiment();
	private Planner planner=new Planner();
	private Tache tache=new Tache();
	
	private Compartiment seleComp;
	private Planner selectedPlanner;
 	private Tache seleTache;
	
	private String mail;
	private String grade;	
	private String nom;
	private String id;
	private String nomCompartiment;
	private String namePlanner;
	private Long idHidden;
	private String type;
	private String description;
	private String etat;
	private Date dateDebut;
	private Date dateEcheance;
 
	@PostConstruct
	public void init(){
		
		System.out.println(" -- - --- --- -GestionBean Start- --- --- ");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser");
 
	}
	
	// liste Tache by id user
    public void onRowSelect(SelectEvent event) {
         
    }
	public List<Tache> listeAllTache()
	{	
		
		List<Tache> listeTache=new ArrayList<>();
		if (logedUser.getGrade().equals("admin"))
			listeTache=tacheDao.getAllTache();
		else
		listeTache=tacheDao.getAllTacheUser(logedUser.getId());		
		return listeTache;
	}
	
	// liste Compartiment by id user
	
	public List<Compartiment> listeAllCompartiment(){
		List<Compartiment> listeCompartiment=new ArrayList<>();
		if (logedUser.getGrade().equals("admin"))
			listeCompartiment=compartimentDao.getAllCompartiment();
		else
		listeCompartiment=compartimentDao.getAllCompartiment(logedUser.getId());
		
		return listeCompartiment;
	}
	
	// liste planner by id user
	
	public List<Planner> listeAllPlanner(){
		List<Planner> listePlanner=new ArrayList<>();
		if (logedUser.getGrade().equals("admin"))
			listePlanner=plannerDao.getAllPlanner();
		else
		listePlanner=plannerDao.getAllPlannerById(logedUser.getId());
		return listePlanner;
	}
	
	
	// supprimer Tache
	
    public void supprimer() {
    	System.out.println("----start supprimer Tache-----");
    	tacheDao.remove(deletedTache);
    	commentDao.deleteTacheComm(deletedTache.getId());
    	System.out.println("end supprimer Tache");    	 
        System.out.println("--- start supprimer TacheUPC ----- ");
    	   tacheUPCDao.delete( deletedTache.getId()); 
    	System.out.println("end supprimer TacheUPC");
	


        
    }
 // supprimer Compartiment
   
    public void supprimerCom(){
    	System.out.println("----start supprimer Compartiment-----");
    	
    	compartimentDao.removeCom(deletedCompartiment);    	
        
    	compartimentAffPlannerUserDao.deletComp(deletedCompartiment.getId());
    	 
    	tacheDao.removeCompa(deletedCompartiment.getId());
    	
    	tacheUPCDao.deleteComp(deletedCompartiment.getId());
    	 
    	System.out.println("----end supprimer Compartiment-----");
    	
    }
    
 // supprimer Planner
     
public void supprimerPlanner(){
	System.out.println("---- start supprimer Planner -----");
	
		plannerDao.remove(deletedPlanner);
		
		supPlannerDao.deleteSupPlanner(deletedPlanner.getId());
		
		affectationPlannerUserDao.deletePlannerAffUser(deletedPlanner.getId());
		
		compartimentDao.deletePlannerCompartiment(deletedPlanner.getId());
		
		compartimentAffPlannerUserDao.deletePlannerAffComp(deletedPlanner.getId());
		
		tacheDao.deletePlannerTache(deletedPlanner.getId());
		
		tacheUPCDao.deletePlannerAffTacheUPC(deletedPlanner.getId());
		
		System.out.println("----end supprimer Planner-----");
		
		
	
}





///////////////EDIT Compartiment

 
public void updateCom() {
	
	System.out.println("start update compartiment");
 
	 
	 compartimentDao.updateCompartiment(seleComp);
//          
     System.out.println("end update Compartiment");
}
 
 
//public void onRowEdit(RowEditEvent event) {
//	System.out.println("start update compartiment");
//	Compartiment cp=(Compartiment)event.getObject();
//	 compartimentDao.updateCompartiment((Compartiment)event.getObject());
//	 System.out.println("end update Compartiment");
//	 
// 
//}
//
//public void onRowCancel(RowEditEvent event) {
// 
//}
 



///////////////EDIT Planner



public void updatePlanner() {
	System.out.println("start update planner");
	
	plannerDao.updatePlanner( selectedPlanner);      
   
     
     System.out.println("end update planner");
}
 
 



///////////////EDIT Tache
 



	public void updateTache(){
	System.out.println("start update Tache AND TacheUPC");
	 
 
	tacheDao.updateTache(seleTache);
	tacheUPCDao.updateTacheUPC(seleTache.getId(),seleTache.getEtat());
	 
	
	System.out.println("end update Tache AND TacheUPC");
	}
	
//	public void onRowEditTache(RowEditEvent event) {
//		System.out.println("start update Tache");
//		tacheDao.updateTache((Tache)event.getObject());		 
//		System.out.println("end update Tache");
//		
//		
//	}
//	 
//	public void onRowCancelTache(RowEditEvent event) {
//		 
//	}
	
	
	 
 
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	public CompartimentAffPlannerUserDao getCompartimentAffPlannerUserDao() {
		return compartimentAffPlannerUserDao;
	}

	public void setCompartimentAffPlannerUserDao(CompartimentAffPlannerUserDao compartimentAffPlannerUserDao) {
		this.compartimentAffPlannerUserDao = compartimentAffPlannerUserDao;
	}

	public PlannerDao getPlannerDao() {
		return plannerDao;
	}

	public void setPlannerDao(PlannerDao plannerDao) {
		this.plannerDao = plannerDao;
	}

	public Planner getDeletedPlanner() {
		return deletedPlanner;
	}

	public void setDeletedPlanner(Planner deletedPlanner) {
		this.deletedPlanner = deletedPlanner;
	}

	public AffectationPlannerUserDao getAffectationPlannerUserDao() {
		return affectationPlannerUserDao;
	}

	public void setAffectationPlannerUserDao(AffectationPlannerUserDao affectationPlannerUserDao) {
		this.affectationPlannerUserDao = affectationPlannerUserDao;
	}

	public Compartiment getCompartiment() {
		return compartiment;
	}

	public void setCompartiment(Compartiment compartiment) {
		this.compartiment = compartiment;
	}

	public String getNomCompartiment() {
		return nomCompartiment;
	}

	public void setNomCompartiment(String nomCompartiment) {
		this.nomCompartiment = nomCompartiment;
	}

	public Long getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(Long idHidden) {
		this.idHidden = idHidden;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

 
 

	public Compartiment getSeleComp() {
		return seleComp;
	}

	public void setSeleComp(Compartiment seleComp) {
		this.seleComp = seleComp;
	}

	public String getNamePlanner() {
		return namePlanner;
	}

	public void setNamePlanner(String namePlanner) {
		this.namePlanner = namePlanner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Planner getSelectedPlanner() {
		return selectedPlanner;
	}

	public void setSelectedPlanner(Planner selectedPlanner) {
		this.selectedPlanner = selectedPlanner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

 
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Tache getSeleTache() {
		return seleTache;
	}

	public void setSeleTache(Tache seleTache) {
		this.seleTache = seleTache;
	}

 

 
 
 
 
	
	
	

}
