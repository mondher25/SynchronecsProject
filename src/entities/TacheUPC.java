package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity 
public class TacheUPC {

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 

 
 @OneToOne
 @JoinColumn(name="planner_id")
 private Planner planner;
 
 @OneToOne
 @JoinColumn(name="compartiment_id")
 private Compartiment compartiment;

 @OneToOne
 @JoinColumn(name="tache_id")
 private Tache tache;
 
 @OneToOne
 @JoinColumn(name="user_id")
 private User user;
 
 private String etat;
 
 private String comment; 
 // SEtter and Getter

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

public Compartiment getCompartiment() {
	return compartiment;
}

public void setCompartiment(Compartiment compartiment) {
	this.compartiment = compartiment;
}

public Tache getTache() {
	return tache;
}

public void setTache(Tache tache) {
	this.tache = tache;
}

public String getEtat() {
	return etat;
}

public void setEtat(String etat) {
	this.etat = etat;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

 
 

}
