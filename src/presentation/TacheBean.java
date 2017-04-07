package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import dao.CompartimentAffPlannerUserDao;
import dao.CompartimentDao;
import dao.CompteDao;

import dao.PlannerDao;
import dao.TacheDao;
import dao.TacheUPCDao;
import dao.UserDao;
import entities.AffectationPlannerUser;
import entities.Compartiment;
import entities.CompartimentAffPlannerUser;
import entities.Compte;
import entities.Planner;
import entities.Tache;
import entities.TacheUPC;
import entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "tacheCP")
@ViewScoped
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
	CompartimentAffPlannerUserDao compartimentAffPlannerUserDao;
	
	@EJB
	TacheUPCDao tacheUPCDao;

	private Tache newTache = new Tache();
	private Compartiment compart = new Compartiment();
	private Planner planner = new Planner();
	private Planner selplan;
	private Compartiment selComp;
	private String selectedMail;
	private User connectedUser;
	private CompartimentAffPlannerUser selctedCAPU;
	private List<CompartimentAffPlannerUser> finalListUserString ;
	private TacheUPC tacheUPCa =new TacheUPC();
	  
	


	private Date dateDebut;
	private Date dateEcheance;
	private String description;
	private String etat;
	private String idp;
	private String mail;
	private String idCom;
	private String grade;
	private String idTache;

	
	
	
	@PostConstruct
	public void init() {
		System.out.println("inti start tache");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		grade = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grade");

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idp = params.get("idp");
		System.out.println("init idplanner= " + idp);
		connectedUser = userDao.getUserByMailId(mail);
		System.out.println("init : id user connecte =" + mail);


	}

	public Planner selectedPlanner() {
		return selplan = plannerDao.getPlannerById(Long.parseLong(idp));
	}

	public Compartiment selectedCompartiment() {
		return selComp = compartimentDao.getCompartimentById(Long.parseLong(idCom));
	}



 
		 

		// for(CompartimentAffPlannerUser u :finalListUserString ){
		//
		// User user;
		// user = u.getUser();
		//
		// CompartimentAffPlannerUser compPlaUsr2 =new
		// CompartimentAffPlannerUser();
		//// compPlaUsr2.setPlanner(selectedPlanner);
		//// compPlaUsr2.setUser(user);
		//// compPlaUsr2.setCompartiment(comp);
		//// CompartimentAffPlannerUserDao.AddCompByPlannerUser(compPlaUsr2);
		// }
	 

	public void addTache() {

		System.out.println("Start add tache");
		newTache.setUserGrade(connectedUser.getGrade());
		newTache.setPlanner(selectedPlanner());
		newTache.setCompartiment(selectedCompartiment());
		newTache.setUser(connectedUser);
		
		tacheDao.addTache(newTache);
		TacheUPC tacheUPC =new TacheUPC();
			
			tacheUPC.setCompartiment(selectedCompartiment());
			tacheUPC.setPlanner(selectedPlanner());			 
			tacheUPC.setUser(userDao.getUserByMailId(selectedMail));
			tacheUPC.setTache(newTache);
			tacheUPC.setEtat(newTache.getEtat());
			tacheUPCDao.addAffTache(tacheUPC);

		

		newTache = new Tache();
		
		

		System.out.println("end add tache");
	}

	public List<TacheUPC> listeTacheByComparAndComp() {
		List<TacheUPC> listeTacheCmCp = new ArrayList<>();
		selectedPlanner();
		selectedCompartiment();
		listeTacheCmCp = tacheUPCDao.getTacheByPlannerCompartiment(selectedPlanner().getId(), selectedCompartiment().getId());
		System.out.println("idCom = " + idCom);
		System.out.println("end Liste tacheByCompartiment");
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

	public List<CompartimentAffPlannerUser> getFinalListUserString() {
		if (connectedUser.getGrade().equalsIgnoreCase("admin"))
		{
			finalListUserString=compartimentAffPlannerUserDao.getUserByPlannerAndComAff(selectedPlanner().getId(),
		
				selectedCompartiment().getId());
		}
		return finalListUserString;
	}

	public void setFinalListUserString(List<CompartimentAffPlannerUser> finalListUserString) {
		this.finalListUserString = finalListUserString;
		 
	}

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

	public TacheUPC getTacheUPCa() {
		return tacheUPCa;
	}

	public void setTacheUPCa(TacheUPC tacheUPCa) {
		this.tacheUPCa = tacheUPCa;
	}

 


 


}
