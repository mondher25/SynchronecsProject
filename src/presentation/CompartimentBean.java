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

import dao.CompartimentDao;
import dao.CompteDao;
import dao.PlannerDao;
import entities.Compartiment;
import entities.Compte;
import entities.Planner;

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

	private Planner planner = new Planner();
	private Planner selectedPlanner = new Planner();
	private Compartiment comp = new Compartiment();
	private Compte connectedUser;

	private String name;
	private String mail;
	private String idCom;
	private String idp;
	private String idUser;

	@PostConstruct
	public void init() {
		System.out.println("compartiment");
		mail = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		idUser=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUser");

		System.out.println(mail);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		idp = params.get("idp");
		connectedUser=compteDao.getCompteById(Long.parseLong(idUser));
		System.out.println("init : id user connecte ="+ Long.parseLong(idUser));

	}

	public Planner selectedPlanner() {
		return selectedPlanner = plan.getPlannerById(Long.parseLong(idp));
	}

	public void addCom() {
		System.out.println("start add compartiment");
		System.out.println("idPlanner = " + idp);
		comp.setPlanner(selectedPlanner());
		comp.setCompte(connectedUser);
		com.addCompartiment(comp);
		 
		
		comp = new Compartiment();
		System.out.println("end add compartiment");

	}
	
	public List<Compartiment> ListeCompByPlannerAndCompte() {
		List<Compartiment> listComPlCp = new ArrayList<>();
		listComPlCp=com.getListCompartimentByPlannerAndCompte(selectedPlanner().getId(), connectedUser.getId());
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
