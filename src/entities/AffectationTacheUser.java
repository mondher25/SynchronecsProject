package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class AffectationTacheUser {

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 
 @ManyToOne
 @JoinColumn(name="user_id")
 private AffectationPlannerUser affectationPlannerUser;

}
