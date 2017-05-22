package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import dao.AffectationPlannerUserDao;
import dao.CommentDao;
import dao.CompartimentAffPlannerUserDao;
import dao.CompartimentDao; 
import dao.PlannerDao;
import dao.TacheDao;
import dao.TacheUPCDao;
import dao.UserDao;
import entities.AffectationPlannerUser;
import entities.Comment;
import entities.Compartiment;
import entities.CompartimentAffPlannerUser;
import entities.Planner;
import entities.Tache;
import entities.TacheUPC;
import entities.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 

@ManagedBean(name = "tacheCP")
@SessionScoped
public class TacheBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private TacheDao tacheDao;

	@EJB
	private PlannerDao plannerDao;

	@EJB
	private CompartimentDao compartimentDao;

	@EJB
	private UserDao userDao;

	@EJB
	private CompartimentAffPlannerUserDao compartimentAffPlannerUserDao;
	
	@EJB
	private TacheUPCDao tacheUPCDao;
	
	@EJB
	private CommentDao commentDao;	
	
	@EJB
	private AffectationPlannerUserDao affectationPlannerUserDao;

	

	private Tache newTache = new Tache();
	private Compartiment compart = new Compartiment();
	private Planner planner = new Planner();
	private Comment comment=new Comment();
	 
	private Planner selplan;
	private Compartiment selComp;
	private String selectedMail;
	private User connectedUser;
	private CompartimentAffPlannerUser compPlaUsr3 =new CompartimentAffPlannerUser();
	private AffectationPlannerUser affectationPlannerUser1 = new AffectationPlannerUser();
	private CompartimentAffPlannerUser selctedCAPU;
//	private List<User> finalListUserString ;
	private String selectedUserMail;
 	private TacheUPC tacheUPC =new TacheUPC();
 	private TacheUPC tacheUPCu =new TacheUPC();
	private User logedUser;  
	private Tache selectedTache;
	private User user;

	private Date dateDebut;
	private Date dateEcheance;
	private String description;
	private String etat;
	private String idp;
	private String mail;
	private String idCom;
	private String grade;
	private String idTache;
	private String commentText;
	private String nomTache;
	
	
	
	
	@PostConstruct
	public void init() {
		System.out.println("inti start tache");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser");
		 
	 
	 
//		FacesContext fc = FacesContext.getCurrentInstance();
//		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//		idp = params.get("idp");
		
		 
		 


	}

	public Planner selectedPlanner() {
		return selplan = plannerDao.getPlannerById(Long.parseLong(idp));
	}

	public Compartiment selectedCompartiment() {

		return selComp = compartimentDao.getCompartimentById(Long.parseLong(idCom));
		
	}


	public boolean rendered(){
		boolean display=false ;
		if(logedUser.getGrade().equalsIgnoreCase("admin") || logedUser.getId() == affectationPlannerUser1.getSuperviseur_id()){
			 
			return display=true;
			
		}else
			return display;
		
	}
	
	
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
 
		 

		// for(CompartimentAffPlannerUser u :finalListUserString ){
		//
		// User user;
		// user = u.getUser();
		//
		// CompartimentAffPlannerUser compPlaUsr2 =new
		// ClisteTacheByComparAndComfompartimentAfffPlannerUser();
		//// compPlaUsr2.setPlanner(selectedPlanner);
		//// compPlaUsr2.setUser(user);
		//// compPlaUsr2.setCompartiment(comp);
		//// CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr2);
		// }
	 

	public void addTache() {
		
		selectedPlanner() ;
		selectedCompartiment();
 		if(selectedUserMail !=null)
 			user=userDao.getUserByMailId(selectedUserMail.trim());
		
		System.out.println("Start add tache");
		newTache.setUserGrade(logedUser.getGrade());
		newTache.setPlanner(selectedPlanner());
		newTache.setCompartiment(selectedCompartiment());	
		 
		newTache.setUser(logedUser);
		tacheDao.addTache(newTache);		
		
 				tacheUPC.setCompartiment(selectedCompartiment());
 				tacheUPC.setPlanner(selectedPlanner());	
 				tacheUPC.setUser(logedUser); 				
 				tacheUPC.setTache(newTache);
 				tacheUPC.setEtat(newTache.getEtat());
 				tacheUPC.setNomTache(newTache.getNomTache());
 				tacheUPC.setUserGrade(logedUser.getGrade());
 				tacheUPC.setDateEcheance(newTache.getDateEcheance());
 				tacheUPCDao.addAffTache(tacheUPC);	
 				
 			  		tacheUPCu.setCompartiment(selectedCompartiment());
 			  		tacheUPCu.setPlanner(selectedPlanner());	
 			  		
 			  		if(selectedUserMail != null)	{
 			  			tacheUPCu.setUser(userDao.getUserByMailId(selectedUserMail.trim())); 
 			  			affectationPlannerUser1.setUser(userDao.getUserByMailId(selectedUserMail.trim()));
 			  			compPlaUsr3.setUser(userDao.getUserByMailId(selectedUserMail.trim()));
 			  		} 	 				 
 			  		else
 			  		{
 			  			tacheUPCu.setUser(logedUser); 
 			  			affectationPlannerUser1.setUser(logedUser);
 			  			compPlaUsr3.setUser(logedUser);
 			  			
 			  		}
 			  		
 	 				tacheUPCu.setTache(newTache);
 	 				tacheUPCu.setEtat(newTache.getEtat());
 	 				tacheUPCu.setNomTache(newTache.getNomTache());
 	 				tacheUPCu.setUserGrade(user.getGrade());
 	 				tacheUPCu.setDateEcheance(newTache.getDateEcheance());
 	 				tacheUPCDao.addAffTache(tacheUPCu);	
 					
 	 					 
		 		 		compPlaUsr3.setCompartiment(selectedCompartiment());
		 		 		compPlaUsr3.setPlanner(selectedPlanner()); 		
		 		 		compartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr3);
		 		 		 	 				 		 		
				 		 		affectationPlannerUser1.setPlanner(selectedPlanner());
				 		 		affectationPlannerUser1.setNomSociete(logedUser.getNomSociete());
				 		 		
				 		 		if (selectedPlanner().isEtat() == false)
				 		 		affectationPlannerUser1.setEtat(false);
				 		 		else
				 		 		affectationPlannerUser1.setEtat(true); 	
				 		 		
 				 		 		affectationPlannerUser1.setSuperviseur_id(selectedPlanner().getSuperviseur_id());
				 		 		affectationPlannerUserDao.addAff(affectationPlannerUser1);
 					 
 			  	
				newTache = new Tache();
				tacheUPC =new TacheUPC();
				tacheUPCu =new TacheUPC();
				compPlaUsr3 =new CompartimentAffPlannerUser();
				affectationPlannerUser1 = new AffectationPlannerUser();
 				System.out.println("end add tache");


	}
	
  public List<TacheUPC> getDateTache(){
	  List<TacheUPC> liste=new ArrayList<TacheUPC>();
	  liste=tacheUPCDao.getTacheByDate(logedUser.getId());
	  return liste;
  }

  public List<TacheUPC> listeTacheTermine(){
	  List<TacheUPC> liste=new ArrayList<TacheUPC>();
	  liste=tacheUPCDao.getTacheTermine(logedUser.getId());
	  return liste;
  }

  public List<TacheUPC> listeTacheEncours(){
	  List<TacheUPC> liste=new ArrayList<TacheUPC>();
	  liste=tacheUPCDao.getTacheEnCour(logedUser.getId());
	  return liste;
  }

  public List<TacheUPC> listeTacheNonCommence(){
	  List<TacheUPC> liste=new ArrayList<TacheUPC>();
	  liste=tacheUPCDao.getTacheNonCommence(logedUser.getId());
	  return liste;
  }

	public List<TacheUPC> listeTacheByComparAndComp() {
		System.out.println("-------------START Liste tacheByCompartiment----------");
		List<TacheUPC> listeTacheCmCp = new ArrayList<>();
		selectedPlanner();
		selectedCompartiment(); 
		listeTacheCmCp = tacheUPCDao.getTacheByUser(selectedPlanner().getId(), selectedCompartiment().getId());
		System.out.println("idCom = " + idCom);
		System.out.println("-----------------end Liste tacheByCompartiment---------");
		return listeTacheCmCp;
	}
     
	
	
	
//	public List<Tache> listeTacheByComp() {
//		List<Tache> listeTache = new ArrayList<>();
//		selectedPlanner();
//		selectedCompartiment();
//		listeTache = tacheDao.getTacheByCompartiment(Long.parseLong(idCom));
//		System.out.println("idCom = " + idCom);
//		System.out.println("end Liste tacheByCompartiment");
//		return listeTache;
//	}

	// Getter and Setter
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public CompartimentDao getCompartimentDao() {
		return compartimentDao;
	}

	public void setCompartimentDao(CompartimentDao compartimentDao) {
		this.compartimentDao = compartimentDao;
	}

	public Tache getNewTache() {
		return newTache;
	}

	public void setNewTache(Tache newTache) {
		this.newTache = newTache;
	}

	public Compartiment getCompart() {
		return compart;
	}

	public void setCompart(Compartiment compart) {
		this.compart = compart;
	}

	public Planner getSelplan() {
		return selplan;
	}

	public void setSelplan(Planner selplan) {
		this.selplan = selplan;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}

	public String getIdCom() {
		return idCom;
	}

	public void setIdCom(String idCom) {
		this.idCom = idCom;
	}

	public Compartiment getSelComp() {
		return selComp;
	}

	public void setSelComp(Compartiment selComp) {
		this.selComp = selComp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdp() {
		return idp;
	}

	public void setIdp(String idp) {
		this.idp = idp;
	}

	public TacheDao getTacheDao() {
		return tacheDao;
	}

	public void setTacheDao(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
	}

	public PlannerDao getPlannerDao() {
		return plannerDao;
	}

	public void setPlannerDao(PlannerDao plannerDao) {
		this.plannerDao = plannerDao;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public CompartimentAffPlannerUserDao getCompartimentAffPlannerUserDao() {
		return compartimentAffPlannerUserDao;
	}

	public void setCompartimentAffPlannerUserDao(CompartimentAffPlannerUserDao compartimentAffPlannerUserDao) {
		this.compartimentAffPlannerUserDao = compartimentAffPlannerUserDao;
	}

//	public List<CompartimentAffPlannerUser> getFinalListUserString() {
//		
//		if (logedUser.getGrade().equalsIgnoreCase("admin"))
//		{
//			finalListUserString=compartimentAffPlannerUserDao.getUserByPlannerAndComAff(selectedPlanner().getId(),
//		
//				selectedCompartiment().getId());
//		}
//		return finalListUserString;
//	}
//
//	public void setFinalListUserString(List<CompartimentAffPlannerUser> finalListUserString) {
//		this.finalListUserString = finalListUserString;
//		 
//	}

	public CompartimentAffPlannerUser getSelctedCAPU() {
		return selctedCAPU;
	}

	public void setSelctedCAPU(CompartimentAffPlannerUser selctedCAPU) {
		this.selctedCAPU = selctedCAPU;
	}

	public TacheUPCDao getTacheUPCDao() {
		return tacheUPCDao;
	}

	public void setTacheUPCDao(TacheUPCDao tacheUPCDao) {
		this.tacheUPCDao = tacheUPCDao;
	}

	public String getSelectedMail() {
		return selectedMail;
	}

	public void setSelectedMail(String selectedMail) {
		this.selectedMail = selectedMail;
	}

	public String getIdTache() {
		return idTache;
	}

	public void setIdTache(String idTache) {
		this.idTache = idTache;
	}

 
	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getLogedUser() {
		return logedUser;
	}

	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}

	public Tache getSelectedTache() {
		return selectedTache;
	}

	public void setSelectedTache(Tache selectedTache) {
		this.selectedTache = selectedTache;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public AffectationPlannerUserDao getAffectationPlannerUserDao() {
		return affectationPlannerUserDao;
	}

	public void setAffectationPlannerUserDao(AffectationPlannerUserDao affectationPlannerUserDao) {
		this.affectationPlannerUserDao = affectationPlannerUserDao;
	}

	public CompartimentAffPlannerUser getCompPlaUsr3() {
		return compPlaUsr3;
	}

	public void setCompPlaUsr3(CompartimentAffPlannerUser compPlaUsr3) {
		this.compPlaUsr3 = compPlaUsr3;
	}

	public AffectationPlannerUser getAffectationPlannerUser1() {
		return affectationPlannerUser1;
	}

	public void setAffectationPlannerUser1(AffectationPlannerUser affectationPlannerUser1) {
		this.affectationPlannerUser1 = affectationPlannerUser1;
	}

	public String getSelectedUserMail() {
		return selectedUserMail;
	}

	public void setSelectedUserMail(String selectedUserMail) {
		this.selectedUserMail = selectedUserMail;
	}

	public TacheUPC getTacheUPC() {
		return tacheUPC;
	}

	public void setTacheUPC(TacheUPC tacheUPC) {
		this.tacheUPC = tacheUPC;
	}

	public TacheUPC getTacheUPCu() {
		return tacheUPCu;
	}

	public void setTacheUPCu(TacheUPC tacheUPCu) {
		this.tacheUPCu = tacheUPCu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNomTache() {
		return nomTache;
	}

	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}




 


}
