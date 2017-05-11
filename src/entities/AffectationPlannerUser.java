package entities;

 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AffectationPlannerUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
    @OneToOne
    @JoinColumn(name="planner_id")
	private Planner planner;
	
    @OneToOne
    @JoinColumn(name="user_id")
    private User  user;
 
    
     
	
	private boolean etat;
	
	private Long superviseur_id;
	
	
	//Getter and Setter
	
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Long getSuperviseur_id() {
		return superviseur_id;
	}

	public void setSuperviseur_id(Long superviseur_id) {
		this.superviseur_id = superviseur_id;
	}

 
 
 

 

 

 

 


 
}
