package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class TacheUPC {

 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 
 @ManyToOne
 @JoinColumn(name="user_id")
 private User user;
 
 @ManyToOne
 @JoinColumn(name="planner_id")
 private Planner planner;
 
 @ManyToOne
 @JoinColumn(name="compartiment_id")
 private Compartiment compartiment;
 

}
