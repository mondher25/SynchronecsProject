package entities;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table(name="planner")
@Entity
public class Planner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String namePlanner;
	

	@Column(name="type")
	private String type;
	

	@OneToOne
	@JoinColumn(name="user_mail_id")
	private User user;
 
	private String userGrade;
	
	
		
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

 
	

}
