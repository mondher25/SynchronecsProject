package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CompartimentAffPlannerUser {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="compartiment_id")
	private Compartiment compartiment;
	
	@OneToOne
	@JoinColumn(name="planner_id")
	private Planner planner;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;


	
	
	
	//Getter and Setter
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
