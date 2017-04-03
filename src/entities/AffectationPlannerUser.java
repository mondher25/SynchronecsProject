package entities;

 

 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
 
 

@Entity
public class AffectationPlannerUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	 
    @ManyToOne
    @JoinColumn(name="planner_id")
	private Planner planner;
	
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    private String nomSociete;
    
 
	
    private String nomPlanner;
	
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

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

 
	public String getNomPlanner() {
		return nomPlanner;
	}

	public void setNomPlanner(String nomPlanner) {
		this.nomPlanner = nomPlanner;
	}

 

 

 

 

 

 

 

 

 


 
}
