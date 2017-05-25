package presentation;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.PlannerDao;
import dao.SupPlannerDao;
import dao.UserDao;
import dao.AffectationPlannerUserDao;
import dao.CompartimentAffPlannerUserDao;
import entities.Planner;
import entities.SupPlanner;
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
	
	@EJB
	private CompartimentAffPlannerUserDao compartimentAffPlannerUserDao;
	
	@EJB
	private SupPlannerDao supPlannerDao; 
	
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
	private String selectedUserMail;  
	private String selectedStringPlan; 
	private List<String>finalListPlanner=new ArrayList<>();   
	
	private Planner planner =new Planner();
	private Planner newPlanner =new Planner();
	private Planner supPlanner =new Planner();
	private SupPlanner sup=new SupPlanner();
	private User compteUser=new User();
	private User connectedUser;
	private User logedUser;
	private User user;
//	Iterator<User> it=finalListUserObject.listIterator();
	 

	@PostConstruct
	public void Init(){
		System.out.println(" -- - --- --- -planBean- --- --- -------");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser"); 
	 System.out.println("mail LoguedUser(planner) :"+logedUser.getMail());
	 System.out.println("id LoguedUser(planner) :"+logedUser.getId());
 
	
	}
	

	
	//////AUTO
//    public List<String> completeTheme(String query) {
//    	
//
//            
////            if(logedUser.getGrade().equals("admin") ){
//                List<User> allUsers = userDao.getUser();
//                 
//                List<String> filteredUsers = new ArrayList<String>();
//                
//            for (int i = 0; i < allUsers.size(); i++) {
//            	User skin =allUsers.get(i);
//            		 
//            	
//                if(skin.getMail().toLowerCase().startsWith(query)) {
//                	filteredUsers.add(skin.getMail());
//                }
//            }
//             
//            return filteredUsers;
////            }
////            else
////    		return null;
//    	 
//    }
    

	

	public void addNewPlanner(){
	 System.out.println("----start add Planner---");
	 AffectationPlannerUser affectationPlannerUser1 = new AffectationPlannerUser();
	 
	 if (selectedUserMail !=null)
	 user=userDao.getUserByMailId(selectedUserMail);
	 
	 	planner.setUser(logedUser);
		planner.setUserGrade(logedUser.getGrade());	
		planner.setNomSociete(logedUser.getNomSociete());	
		if(user != null)
			planner.setSuperviseur_id(user.getId());
		else
			planner.setSuperviseur_id(logedUser.getId());
		plannerDao.AddPlanner(planner); 
		
		
		if(selectedUserMail !=null && user !=null )
		{
			sup.setUser(user);
			sup.setEtatsupervise("oui");
			affectationPlannerUser1.setEtatsupervise("oui");
		}
		else
			{
			sup.setUser(logedUser);
			sup.setEtatsupervise("non");
			affectationPlannerUser1.setEtatsupervise("non");
			}
		sup.setPlanner(planner);
		supPlannerDao.addSuperviseur(sup);
		
 		
 		affectationPlannerUser1.setPlanner(planner);
 		affectationPlannerUser1.setNomSociete(logedUser.getNomSociete());
 		affectationPlannerUser1.setUser(logedUser);
 		affectationPlannerUser1.setSuperviseur_id(planner.getSuperviseur_id());
 		if (planner.isEtat() == false)
 		affectationPlannerUser1.setEtat(false);
 		else
 		affectationPlannerUser1.setEtat(true);	
 		affectationPlannerUserDao.addAff(affectationPlannerUser1);
//
//		if (planner.isEtat() == false    ) {
//			 
//			for(String u :finalListUserString){
//				finalListUserString= new ArrayList<>();
//			 
//			User userId=userDao.getUserByMailId(u);
// 			AffectationPlannerUser affectationPlannerUser = new AffectationPlannerUser();
//		    affectationPlannerUser.setPlanner(planner);
//		    affectationPlannerUser.setEtat(false);
//		    affectationPlannerUser.setUser(userId);	
// 		    affectationPlannerUserDao.addAff(affectationPlannerUser);
//		    			 
//			}
//			
//
//		} else if (planner.isEtat() == true)
//	    	{
//		    		List<User> user1 = userDao.getUser();
//		    		for (User u : user1)
//		    		{
//		    			 AffectationPlannerUser affectationPlannerUser2 = new AffectationPlannerUser();
//		    			 affectationPlannerUser2.setPlanner(planner);
//		    			 affectationPlannerUser2.setEtat(true);
//		    			 affectationPlannerUser2.setUser(u);
//		    			 affectationPlannerUserDao.addAff(affectationPlannerUser2);
//		    			 
//		    		}
//	    		
//	    	}	
//					 
		planner=new Planner();	
		affectationPlannerUser1=new AffectationPlannerUser();
		sup=new SupPlanner();
		System.out.println("----end add Planner---");		 
		 
		
	}
	public List<SupPlanner> listeplSuper(){
		List<SupPlanner> lplPublic=new ArrayList<>();
		if(logedUser.getGrade().equalsIgnoreCase("admin"))
			lplPublic=supPlannerDao.getAllSupPlanner();
		else
		lplPublic=supPlannerDao.getSupPlanner(logedUser.getId());
		
		return lplPublic;
	}
	
	public List<SupPlanner> listeplSuperv(){
		List<SupPlanner> lplPublic=new ArrayList<>();		 
		lplPublic=supPlannerDao.getAllSupPlanner();
		return lplPublic;
	}
	

	public List<AffectationPlannerUser> listePlannerPublic(){
		List<AffectationPlannerUser> lplPublic=new ArrayList<>();
//		List<AffectationPlannerUser> lplPrive=new ArrayList<>();
		 
//		lplPrive=affectationPlannerUserDao.getPublicPlannerSuper();
		lplPublic=affectationPlannerUserDao.getPublicPlanner();
//		lplPublic.addAll(lplPrive);
		return lplPublic;
	}
	public List<AffectationPlannerUser> listePlannerPrive(){
		List<AffectationPlannerUser> lplPrive=new ArrayList<>();
		 
		lplPrive=affectationPlannerUserDao.getAllPlanner(logedUser.getId());
		return lplPrive;
	}
	
	public List<AffectationPlannerUser> listePlannerPublicSuper(){
		List<AffectationPlannerUser> lplPrive=new ArrayList<>();
		 
		lplPrive=affectationPlannerUserDao.getPublicPlannerSuper();
		return lplPrive;
	}
	
	
	
	
	public List<AffectationPlannerUser> listePlannerSupervise(){
		
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

 		
		List<AffectationPlannerUser> filtredListPlanner=new ArrayList<>();
		filtredListPlanner=affectationPlannerUserDao.getSuperPlanner(logedUser.getId());
		return filtredListPlanner;	

//		Set<AffectationPlannerUser> set=new HashSet<AffectationPlannerUser>();
//		if(logedUser.getGrade().equalsIgnoreCase("user")){
//			listePlannerAff=affectationPlannerUserDao.listPlannerByAffectationAndId(logedUser.getId());
//	 	 
//	 			 
//		}else if(logedUser.getGrade().equalsIgnoreCase("admin")){
//			listePlannerAff=affectationPlannerUserDao.getAllPlanner(logedUser.getId());
//			
//	 			 
//		}


		 
//		set.addAll(newliste);
//		newliste.clear();
//		newliste.addAll(set);
		
// 		listePlannerAff.addAll(newliste);
// 		listePlannerAff.addAll(liste);
// 		listePlannerAff.addAll(filtredListPlanner);
 		


//		newliste.addAll(affectationPlannerUserDao.getAllPlanner(logedUser.getId()))  ;
//		newliste.addAll(affectationPlannerUserDao.getPublicPlanner());

//		liste=affectationPlannerUserDao.getSuperPlanner(logedUser.getId());
 
//		
//		listePlannerAff=affectationPlannerUserDao.getAllPlanner(logedUser.getId());
//		newliste.addAll(listePlannerAff);
 	
//		newliste.addAll(liste);
//		filtredListPlanner=affectationPlannerUserDao.getPublicPlanner();
//		newliste.addAll(filtredListPlanner)	 ;
//	 
	
		
		
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

  public List<String> completeTheme(String query) {
	    	

          
//        if(logedUser.getGrade().equals("admin") ){
            List<User> allUsers = userDao.getAllUserByGrade();
             
            List<String> filteredUsers = new ArrayList<String>();
            
        for (int i = 0; i < allUsers.size(); i++) {
        	User skin =allUsers.get(i);
        		 
        	
            if(skin.getMail().toLowerCase().startsWith(query)) {
            	filteredUsers.add(skin.getMail());
            } 
        }
         
        return filteredUsers;
//        }
//        else
//		return null;
	 
}
	
	public void addSuperPl(){
		newPlanner=plannerDao.getPlannerById(Long.parseLong(selectedStringPlan));
		sup.setPlanner(newPlanner);
		user=userDao.getUserByMailId(selectedUserMail);
		sup.setUser(user);
	 
		supPlannerDao.addSuperviseur(sup);
	}

	
	
	
//	public boolean rendered(){
//		boolean display=false ;
//		if(logedUser.getGrade().equalsIgnoreCase("user")  ){
//			 
//			return display;
//			
//		}else
//			return display=true;
//		
//	}
 
	public boolean renderedGestionPrive(){
		boolean display=false ;
		if(logedUser.getGrade().equalsIgnoreCase("user")){
			 
			return display;
			
		}else
			return display=true;
		
	}
	
	
	  public List<AffectationPlannerUser> getAllPlannerAd(){
		  List<AffectationPlannerUser> liste=new ArrayList<>();			 
			liste= affectationPlannerUserDao.getAllPlannerAdmin();	 
			
			return liste;
	  }
	  
	  
	  
	  //seter and getter
	

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

	public List<String> getFinalListPlanner() {
		return finalListPlanner=plannerDao.listPlanner();
	}

	public void setFinalListPlanner(List<String> finalListPlanner) {
		this.finalListPlanner = finalListPlanner;
	}



	public SupPlannerDao getSupPlannerDao() {
		return supPlannerDao;
	}



	public void setSupPlannerDao(SupPlannerDao supPlannerDao) {
		this.supPlannerDao = supPlannerDao;
	}



	public String getSelectedUserMail() {
		return selectedUserMail;
	}



	public void setSelectedUserMail(String selectedUserMail) {
		this.selectedUserMail = selectedUserMail;
	}



	public String getSelectedStringPlan() {
		return selectedStringPlan;
	}



	public void setSelectedStringPlan(String selectedStringPlan) {
		this.selectedStringPlan = selectedStringPlan;
	}



	public Planner getNewPlanner() {
		return newPlanner;
	}



	public void setNewPlanner(Planner newPlanner) {
		this.newPlanner = newPlanner;
	}



	public Planner getSupPlanner() {
		return supPlanner;
	}



	public void setSupPlanner(Planner supPlanner) {
		this.supPlanner = supPlanner;
	}



	public SupPlanner getSup() {
		return sup;
	}



	public void setSup(SupPlanner sup) {
		this.sup = sup;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

 
 
	

}
