package entities;

 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Compartiment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nom_com")
	private String nomCompartiment;
	
	
	@ManyToOne
	@JoinColumn(name="planner_id")
	private Planner planner;

	@OneToOne
	@JoinColumn(name="compte_id")
	private Compte compte;
	
	
	
	//Getter and Setter

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomCompartiment() {
		return nomCompartiment;
	}


	public void setNomCompartiment(String nomCompartiment) {
		this.nomCompartiment = nomCompartiment;
	}


	public Planner getPlanner() {
		return planner;
	}


	public void setPlanner(Planner planner) {
		this.planner = planner;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	
	
	
	
	
}
