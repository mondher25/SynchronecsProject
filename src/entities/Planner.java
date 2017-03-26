package entities;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table;


@Table(name="planner")
@Entity
public class Planner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;
	
	@Column(name="name")
	private String namePlanner;
	

	@Column(name="TYPE")
	private String type;
	

		
	//getter setter 

 
	
	


	public void setType(String type) {
		this.type = type;
	}

	public String getNamePlanner() {
		return namePlanner;
	}

	public void setNamePlanner(String namePlanner) {
		this.namePlanner = namePlanner;
	}

 
 

 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	

}
