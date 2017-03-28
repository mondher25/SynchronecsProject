package presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import dao.CompartimentDao;
import dao.CompteDao;
import dao.PlannerDao;
import dao.TacheDao;
import entities.Compartiment;
import entities.Compte;
import entities.Planner;
import entities.Tache;

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
	private TacheDao tache;

	@EJB
	private PlannerDao planDao;

	@EJB
	private CompartimentDao compartimentDao;

	@EJB
	private CompteDao compteDao;
	
	
	private Tache newTache = new Tache();
	private Compartiment compart = new Compartiment();
	private Planner planner = new Planner();
	private Planner selplan;
	private Compartiment selComp;
	private Compte connectedUser;

	private Date dateDebut;
	private Date dateEcheance;

	private String description;
	private String etat;
	private String idp;
	private String mail;
	private String idCom;
	private String idUser;

	@PostConstruct
	public void init() {
		System.out.println("inti start tache");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		idUser=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUser");

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idp = params.get("idp");
		System.out.println("init idplanner= " + idp);
		connectedUser=compteDao.getCompteById(Long.parseLong(idUser));
		System.out.println("init : id user connecte ="+ Long.parseLong(idUser));
	}
	
	

	public Planner selectedPlanner() {
		return selplan = planDao.getPlannerById(Long.parseLong(idp));
	}

	
	public Compartiment selectedCompartiment() {
		return selComp = compartimentDao.getCompartimentById(Long.parseLong(idCom));
	}
	
	

	public void addTache() {
		System.out.println("Start add tache");

		newTache.setPlanner(selectedPlanner());
		newTache.setCompartiment(selectedCompartiment());		
		newTache.setCompte(connectedUser);
		
		tache.addTache(newTache);
		newTache = new Tache();

		System.out.println("end add tache");
	}
	public List<Tache> listeTacheByComparAndComp() {
		List<Tache> listeTacheCmCp = new ArrayList<>();
		selectedPlanner();
		selectedCompartiment();
		listeTacheCmCp = tache.getTacheByCompartimentAndCompte(Long.parseLong(idCom), connectedUser.getId());
		System.out.println("idCom = " + idCom);
		System.out.println("end Liste tacheByCompartiment");
		return listeTacheCmCp;
	}
	
	public List<Tache> listeTacheByComp() {
		List<Tache> listeTache = new ArrayList<>();
		selectedPlanner();
		selectedCompartiment();
		listeTache = tache.getTacheByCompartiment(Long.parseLong(idCom));
		System.out.println("idCom = " + idCom);
		System.out.println("end Liste tacheByCompartiment");
		return listeTache;
	}

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

	public TacheDao getTache() {
		return tache;
	}

	public void setTache(TacheDao tache) {
		this.tache = tache;
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

	public PlannerDao getPlanDao() {
		return planDao;
	}

	public void setPlanDao(PlannerDao planDao) {
		this.planDao = planDao;
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



	public CompteDao getCompteDao() {
		return compteDao;
	}



	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}



	public Compte getConnectedUser() {
		return connectedUser;
	}



	public void setConnectedUser(Compte connectedUser) {
		this.connectedUser = connectedUser;
	}



	public String getIdUser() {
		return idUser;
	}



	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

 
	
}
