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
 
	
 
	private String mail;
	private String password;
	private String idUser;
 
	 
	
 
	
	public String login(){
		System.out.println("Start Login");
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Erreur: Votre adresse E-mail (ou) mot de passe est incorrect", "");
		boolean logedin = false;

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		
		
		
 
	 
 

		logedUser = userDao.login(mail.trim(),password.trim());


		
		if ( logedUser != null) {

			session.setAttribute("mail",logedUser.getMail());
			session.setAttribute("nom",logedUser.getNom());
			session.setAttribute("grade",logedUser.getGrade());
			session.setAttribute("idUser",logedUser.getId()+"");
		 
			logedin = true;

			return "planner/planner.xhtml?faces-redirect=true";

		} 
			if(logedin == false )
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
 
		
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




	public String getIdUser() {
		return idUser;
	}




	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}




 

	
	

	
	
		
	
}
