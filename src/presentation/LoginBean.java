package presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

  
import dao.UserDao;
 import entities.User;
 
 
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;

	@EJB 
	private UserDao userDao;
	
 
	
 
	
	private User user = new User() ;
	 
	
	private User logedUser;
	 
	
	private String type;
	private String mail;
	private String password;
	 
 
	 
	private User newUser=new User();
 
	
	 
	public String login(){
		System.out.println("Start Login");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Erreur: Votre adresse E-mail (ou) mot de passe est incorrect", "");
		boolean logedin = false;

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);


		logedUser = userDao.login(mail.trim(), password.trim());


		
		if ( logedUser != null) {

			session.setAttribute("logedUser",logedUser);

		 
			logedin = true;
			return "planner/planner.xhtml?faces-redirect=true";

		}
					 

	 
	 if(logedin == false )
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
			
			}

 
	public void addNewUser(){
		System.out.println("start add User");
		newUser.setGrade("user");
		 
		 
		newUser.setAddedBy(logedUser.getMail());
		newUser.setNomSociete(logedUser.getNomSociete());
		userDao.createUser(newUser);
		newUser = new User();
		System.out.println("End add User");
	}
	
	public boolean DisplayInsc(){
		boolean display=false;
		if(userDao.getUserByGrade()!=null){
			return display;
		}else
			return display=true;
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
			return "/faces/login.xhtml?faces-redirect=true";
	}
	
	
	
	//Getter Setter 
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}




	public UserDao getUserDao() {
		return userDao;
	}




	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

 

 
	public User getLogedUser() {
		return logedUser;
	}

	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




 



	public String getMail() {
		return mail;
	}




	public void setMail(String mail) {
		this.mail = mail;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




 


	public User getNewUser() {
		return newUser;
	}


	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

 

 

	
	

	
	
		
	
}
