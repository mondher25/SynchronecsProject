package presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.AffectationPlannerUserDao;
import dao.CompartimentDao;
import dao.CompteDao;

import dao.PlannerDao;
import dao.UserDao;
import dao.CompartimentAffPlannerUserDao;
import entities.AffectationPlannerUser;
import entities.Compartiment;
import entities.CompartimentAffPlannerUser;
import entities.Compte;
import entities.Planner;
import entities.User;

@ManagedBean(name = "com")
@ViewScoped
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
	private CompteDao compteDao;
	
	@EJB 
	private UserDao userDao;
 
	@EJB
	private CompartimentAffPlannerUserDao CompartimentAffPlannerUserDao;

	private Planner planner = new Planner();
	private Planner selectedPlanner = new Planner();
	private Compartiment comp = new Compartiment();
	private User connectedUser;

	private String name;
	private String mail;
	private String idCom;
	private String idp;
	private String grade;

	@PostConstruct
	public void init() {
		System.out.println("compartiment");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		grade = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grade");

		System.out.println(mail);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idp = params.get("idp");
		connectedUser=userDao.getUserByMailId(mail);
		System.out.println("init : mail user connecte ="+mail);

	}
 
	public Planner selectedPlanner() {
		return selectedPlanner = plan.getPlannerById(Long.parseLong(idp));
	}

	public void addCom() {
		System.out.println("start add compartiment");
		System.out.println("idPlanner = " + idp);
		comp.setPlanner(selectedPlanner());
		comp.setUser(connectedUser);
		comp.setUserGrade(grade);		
		com.addCompartiment(comp);
		
		System.out.println("planner etat :" +planner.isEtat());
		System.out.println("etat --------------- planner " );
		
		if (selectedPlanner.isEtat() == true)
		{ 	List<User> user1 = userDao.getUser();
		
			for (User u : user1)
			{
				 
				CompartimentAffPlannerUser compPlaUsr =new CompartimentAffPlannerUser();
				compPlaUsr.setCompartiment(comp);
				compPlaUsr.setPlanner(selectedPlanner);
				compPlaUsr.setUser(u);
				CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr);
			 
			}
			
		}
		
		 
		
		comp = new Compartiment();
		System.out.println("end add compartiment");

	}
	
	public List<CompartimentAffPlannerUser> ListeCompByPlannerAndCompte() {
		List<CompartimentAffPlannerUser> listComPlCp = new ArrayList<>();
		listComPlCp=CompartimentAffPlannerUserDao.comparByPlaUsr(mail, selectedPlanner().getId());
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

	public CompteDao getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
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

 
	
}
