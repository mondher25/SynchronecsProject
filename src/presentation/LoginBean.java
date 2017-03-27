package presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dao.CompteDao;
import dao.UserDao;
import entities.Compte;
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
	
	@EJB 
	private CompteDao compteDao;
	
	private User user = new User() ;
	private Compte comptemanager=new Compte();
	
	private User logedUser;
	private Compte logeByCp;
	
	private String type;
	private String mail;
	private String password;
 
	
 
	
	public String login(){
		System.out.println("Start");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Erreur: Votre adresse E-mail (ou) mot de passe est incorrect", "");
		boolean logedin = false;

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		
		
		
 
	switch (type) {
	case "employee":

		logedUser = userDao.login(mail.trim(),password.trim());


		
		if ( logedUser != null) {

			session.setAttribute("mail",mail);
			logedin = true;

			return "planner/planner.xhtml?faces-redirect=true";

		}
			break;
			
		case "employeur":

		logeByCp = compteDao.login(mail.trim(),password.trim());	

		
		if ( logeByCp != null) {

			session.setAttribute("mail", mail);
			logedin = true;

			return "planner/planner.xhtml?faces-redirect=true";

			}
			break;
		 

		}
	 if(logedin == false )
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
 
		
	}

 
	
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
	return "../login.xhtml";
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




	public CompteDao getCompteDao() {
		return compteDao;
	}




	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}




	public Compte getComptemanager() {
		return comptemanager;
	}




	public void setComptemanager(Compte comptemanager) {
		this.comptemanager = comptemanager;
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




	public Compte getLogeByCp() {
		return logeByCp;
	}




	public void setLogeByCp(Compte logeByCp) {
		this.logeByCp = logeByCp;
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




 

	
	

	
	
		
	
}
