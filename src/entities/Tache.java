package entities;

import java.util.Date; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tache {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
    private Date dateEcheance;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	private String etat;
	
	private String description;
	
	private String userGrade;
	
	 
	 
	
	private String nomTache;
	
	@ManyToOne
	@JoinColumn(name="compartiment_id")
	private Compartiment compartiment;
	
	@ManyToOne
	@JoinColumn(name="planner_id")
	private Planner planner;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
 


	
	//Getter and Setter
	
	

	public Long getId() {
		return id;
	}
	public Date getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Compartiment getCompartiment() {
		return compartiment;
	}
	public void setCompartiment(Compartiment compartiment) {
		this.compartiment = compartiment;
	}
	public Planner getPlanner() {
		return planner;
	}
	public void setPlanner(Planner planner) {
		this.planner = planner;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
 
	public String getNomTache() {
		return nomTache;
	}
	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}
 
	
	
	
	
}
