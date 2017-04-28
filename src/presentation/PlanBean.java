package presentation;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

 

import dao.PlannerDao;
import dao.UserDao;
import dao.AffectationPlannerUserDao;
import dao.CompartimentAffPlannerUserDao;
import entities.Planner;
import entities.User;
import entities.AffectationPlannerUser;
import entities.Compartiment;
import entities.CompartimentAffPlannerUser;


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
	
	@EJB
	private CompartimentAffPlannerUserDao compartimentAffPlannerUserDao;
	
	private AffectationPlannerUser affEtat=new  AffectationPlannerUser();
	private List<User> listeUser =new ArrayList<>();
	
	 
	private List<String> finalListUserString;
 
	 
	private String nom;	
	private String mail ;
	private String grade;
	private boolean etat;
	private String nomSociete;
	private String idUser;
	private String idp;
	  
	 
	   
	
	private Planner planner =new Planner();
	private User compteUser=new User();
	private User connectedUser;
	private User logedUser;
	
//	Iterator<User> it=finalListUserObject.listIterator();
	 

	@PostConstruct
	public void Init(){
		System.out.println(" -- - --- --- -planBean- --- --- -------");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser"); 
	 System.out.println("mail LoguedUser(planner) :"+logedUser.getMail());
	 System.out.println("id LoguedUser(planner) :"+logedUser.getId());
 
	
	}
	

	
	//////AUTO
    public List<String> completeTheme(String query) {
    	

            
            if(logedUser.getGrade().equals("admin") ){
                List<User> allUsers = userDao.getUser();
                List<String> filteredUsers = new ArrayList<String>();
            for (int i = 0; i < allUsers.size(); i++) {
            	User skin = allUsers.get(i);
                if(skin.getMail().toLowerCase().startsWith(query)) {
                	filteredUsers.add(skin.getMail());
                }
            }
             
            return filteredUsers;
            }
            else
    		return null;
    	 
    }
    
  
	

	public void addNewPlanner(){
	 System.out.println("----start add Planner---");	
	 	planner.setUser(logedUser);
		planner.setUserGrade(logedUser.getGrade());	
		planner.setNomSociete(logedUser.getNomSociete());
		
		plannerDao.AddPlanner(planner); 
		
		AffectationPlannerUser affectationPlannerUser1 = new AffectationPlannerUser();
		affectationPlannerUser1.setPlanner(planner);
		affectationPlannerUser1.setUser(logedUser);
		if (planner.isEtat() == false)
		affectationPlannerUser1.setEtat(false);
		else
		affectationPlannerUser1.setEtat(true);
		
		 
		affectationPlannerUserDao.addAff(affectationPlannerUser1);

		if (planner.isEtat() == false) {
			
			for(String u :finalListUserString){
				finalListUserString= new ArrayList<>();
			 
			User userId=userDao.getUserByMailId(u);
 			AffectationPlannerUser affectationPlannerUser = new AffectationPlannerUser();
		    affectationPlannerUser.setPlanner(planner);
		    affectationPlannerUser.setEtat(false);
		    affectationPlannerUser.setUser(userId);	 		   
		    affectationPlannerUserDao.addAff(affectationPlannerUser);
		    
		     			 
			}
			

		}
	 	else if (planner.isEtat() == true)
	    	{
		    		List<User> user1 = userDao.getUser();
		    		for (User u : user1)
		    		{
		    			AffectationPlannerUser affectationPlannerUser2 = new AffectationPlannerUser();
		    			 affectationPlannerUser2.setPlanner(planner);
		    			 affectationPlannerUser2.setEtat(true);
		    			 affectationPlannerUser2.setUser(u);
		    			 affectationPlannerUserDao.addAff(affectationPlannerUser2);
		    			 
		    		}
	    		
	    	}	
					 
		planner=new Planner();		
		System.out.println("----end add Planner---");		 
		 
		
	}
	
	public List<AffectationPlannerUser> listePlannerByIdCompte(){
		
		System.out.println("------Start Affichage  Planner----- ");
//		if(grade == "admin"){
//			List<Planner> listPlannerCompte = new ArrayList<Planner>();
//			
//			listPlannerCompte = plannerDao.getAllPlannerByMailAndnomSociete(mail, nomSociete);
//			checketat();
//			System.out.println(" --- -- -- end Liste  Planner --- ");
//			return listPlannerCompte ;
//		}
//		else if(grade == "user")
		
//		{
//		if (planner.getEtat().equals("public")){
//			
//			List<AffectationPlannerUser> listePlannerAff1=new ArrayList<>();
//			listePlannerAff1=affectationPlannerUserDao.getPlannerByNomSocieteAndEtat(etat);			 
//			System.out.println("end Affichage 1");
//			return listePlannerAff1;
//		}
//		else 
//	{
//			

		List<AffectationPlannerUser> listePlannerAff=new ArrayList<>();
		if(logedUser.getGrade().equalsIgnoreCase("user")){
			listePlannerAff=affectationPlannerUserDao.listPlannerByAffectationAndId(logedUser.getId());
	 	 
	 			 
		}else if(logedUser.getGrade().equalsIgnoreCase("admin")){
			listePlannerAff=affectationPlannerUserDao.getAllPlanner(logedUser.getId());
			
	 			 
		}
		return listePlannerAff;
 	}

//		}
// 		return null;
		

		
//	}
	
 
//		public List<Planner> listePlanner(){
//		List<Planner> list = new ArrayList<Planner>();
//		list = plannerDao.getAllPlanner();
//		System.out.println(" --- -- -- end Liste  Planner --- ");
//		return list ;
//		
//	}
//	
//	   	   public void onRowSelect(SelectEvent event) throws IOException {
//		   FacesContext context = FacesContext.getCurrentInstance();	
//		   HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
//		   context.getExternalContext().redirect(req.getContextPath()+"/faces/planner/tache.xhtml");
//	    
//	    }
	
	
	// 		GetterAnd Setter


 
 

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



	public AffectationPlannerUser getAffEtat() {
		return affEtat;
	}



	public void setAffEtat(AffectationPlannerUser affEtat) {
		this.affEtat = affEtat;
	}



	public void setEtat(boolean etat) {
		this.etat = etat;
	}



	public CompartimentAffPlannerUserDao getCompartimentAffPlannerUserDao() {
		return compartimentAffPlannerUserDao;
	}



	public void setCompartimentAffPlannerUserDao(CompartimentAffPlannerUserDao compartimentAffPlannerUserDao) {
		this.compartimentAffPlannerUserDao = compartimentAffPlannerUserDao;
	}



	public boolean isEtat() {
		return etat;
	}



	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



	public User getLogedUser() {
		return logedUser;
	}



	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}



	public String getIdp() {
		return idp;
	}



	public void setIdp(String idp) {
		this.idp = idp;
	}

 
 
	

}
