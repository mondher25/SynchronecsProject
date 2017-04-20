package presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
 
import javax.faces.bean.ManagedBean;
 
import javax.faces.bean.SessionScoped;
 
import javax.faces.context.FacesContext;

import dao.AffectationPlannerUserDao;
import dao.CompartimentDao;
 
import dao.PlannerDao;
import dao.UserDao;
import dao.CompartimentAffPlannerUserDao;
import entities.AffectationPlannerUser;
import entities.Compartiment;
import entities.CompartimentAffPlannerUser;
 import entities.Planner;
import entities.User;

@ManagedBean(name = "com")
@SessionScoped
public class CompartimentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private CompartimentDao com;

	@EJB
	private PlannerDao plan;
	
 
	
	@EJB 
	private UserDao userDao;
 
	@EJB
	private CompartimentAffPlannerUserDao CompartimentAffPlannerUserDao;
	
	@EJB
	private AffectationPlannerUserDao affectationPlannerUserDao;

	private Planner planner = new Planner();
	private Planner selectedPlanner;
	private Compartiment comp = new Compartiment();
	private User connectedUser;
	private User logedUser;
	
	private String name;
	private String mail;
	private String idCom;
	private String idp;
	private String grade;
	
	private List<AffectationPlannerUser> finalListUserString;
 
	@PostConstruct
	public void init() {
		System.out.println("-------- <> compartiment <> ---------");
		logedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser");
 		System.out.println("logedUser id :"+logedUser.getId()+ "mail = : "+ logedUser.getMail());
 		System.out.println("init compartiment id planner:"+idp);
 		 
 		 
//		FacesContext fc = FacesContext.getCurrentInstance();
//		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
//		idp = params.get("idp");
//		
 
 		 
 
		
		
		 
	}
		
//	public String result(){
//		 FacesContext fc = FacesContext.getCurrentInstance();
//	      Map<String,String> params = 
//	         fc.getExternalContext().getRequestParameterMap();
//	      idp =  params.get("idp"); 
//	      return "tache.xhtml?faces-redirect=true";
//	}
 
 
	
  	public Planner selectedPlanner() {
   		return selectedPlanner =plan.getPlannerById(Long.parseLong(idp));
   	}

	public void addCom() {
		
		System.out.println("start add compartiment");
		
		System.out.println("idPlanner = " + selectedPlanner().getId());
		comp.setPlanner(selectedPlanner());
		comp.setUser(logedUser);
		comp.setUserGrade(logedUser.getGrade());		
		com.addCompartiment(comp);
 		selectedPlanner();
 		
//		CompartimentAffPlannerUser compPlaUsr3 =new CompartimentAffPlannerUser();
//		
//		compPlaUsr3.setUser(connectedUser);
//		compPlaUsr3.setCompartiment(comp);
//		compPlaUsr3.setPlanner(selectedPlanner);
//		
//		CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr3);
		
		System.out.println("planner etat :" +planner.isEtat());
		System.out.println("etat --------------- planner " );
		
//		if (selectedPlanner.isEtat() == true)
//		{ 	List<User> user1 = userDao.getUser();
//		
// 
//			for (User u : user1)
//			{				 
//				CompartimentAffPlannerUser compPlaUsr =new CompartimentAffPlannerUser();
//				compPlaUsr.setCompartiment(comp);
//				compPlaUsr.setPlanner(selectedPlanner);
//				compPlaUsr.setUser(u);
//				CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr);
//			 
//			}
//		
//		}
//		 if (selectedPlanner.isEtat() == false){
		finalListUserString =affectationPlannerUserDao.getUserByPlannerAff(selectedPlanner().getId());
			for(AffectationPlannerUser u :finalListUserString ){
				
				User user;
				user = u.getUser();				
				CompartimentAffPlannerUser compPlaUsr2 =new CompartimentAffPlannerUser();				 
				compPlaUsr2.setPlanner(selectedPlanner());				 
				compPlaUsr2.setUser(user);	 		   
				compPlaUsr2.setCompartiment(comp);
				CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr2);
				}

//		}
		
		 
		
		comp = new Compartiment();
		System.out.println("end add compartiment");

	}
	


	public List<CompartimentAffPlannerUser> ListeCompByPlannerAndCompte() {
		List<CompartimentAffPlannerUser> listComPlCp = new ArrayList<>();
		if(logedUser.getGrade().equals("admin") ){
			listComPlCp=CompartimentAffPlannerUserDao.getAllCompartiment(logedUser.getId(),Long.parseLong(idp));
		}else if(logedUser.getGrade().equalsIgnoreCase("user")){
			listComPlCp=CompartimentAffPlannerUserDao.comparByPlaUsr(logedUser.getId(), Long.parseLong(idp));
		}
		 
		System.out.println("end List compartiment");
		return listComPlCp;
	}

//	public List<Compartiment> ListeCompByPlanner() {
//		List<Compartiment> listCom = new ArrayList<>();
//		listCom = com.getListCompartimentByPlanner(selectedPlanner().getId());
//		System.out.println("end List compartiment");
//		return listCom;
//	}

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

	public Compartiment getComp() {
		return comp;
	}

	public void setComp(Compartiment comp) {
		this.comp = comp;
	}

	public CompartimentDao getCom() {
		return com;
	}

	public void setCom(CompartimentDao com) {
		this.com = com;
	}

	public PlannerDao getPlan() {
		return plan;
	}

	public void setPlan(PlannerDao plan) {
		this.plan = plan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdp() {
		return idp;
	}
	public void setIdp(String idp) {
		this.idp = idp;
	}
	public String getIdCom() {
		return idCom;
	}

	public void setIdCom(String idCom) {
		this.idCom = idCom;
	}

 	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public CompartimentAffPlannerUserDao getCompartimentAffPlannerUserDao() {
		return CompartimentAffPlannerUserDao;
	}

	public void setCompartimentAffPlannerUserDao(CompartimentAffPlannerUserDao compartimentAffPlannerUserDao) {
		CompartimentAffPlannerUserDao = compartimentAffPlannerUserDao;
	}

	public AffectationPlannerUserDao getAffectationPlannerUserDao() {
		return affectationPlannerUserDao;
	}

	public void setAffectationPlannerUserDao(AffectationPlannerUserDao affectationPlannerUserDao) {
		this.affectationPlannerUserDao = affectationPlannerUserDao;
	}

	public List<AffectationPlannerUser> getFinalListUserString() {
		return finalListUserString;
	}

	public void setFinalListUserString(List<AffectationPlannerUser> finalListUserString) {
		this.finalListUserString = finalListUserString;
	}



	public User getLogedUser() {
		return logedUser;
	}



	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}

 
 

 
	
}
