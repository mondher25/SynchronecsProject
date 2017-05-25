package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SupPlanner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private User user;
	
	@ManyToOne
	private Planner planner;
	
	private String etatsupervise;
 


	
	
	//Getter and Setter
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Planner getPlanner() {
		return planner;
	}

	public void setPlanner(Planner planner) {
		this.planner = planner;
	}

	public String getEtatsupervise() {
		return etatsupervise;
	}

	public void setEtatsupervise(String etatsupervise) {
		this.etatsupervise = etatsupervise;
	}

 
	
}
