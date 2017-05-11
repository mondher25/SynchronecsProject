package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

 
 
import dao.UserDao;
 
 
import entities.User;
import service.SendMail;

@ManagedBean(name="userBean")
@ViewScoped
public class userBean {

	
	@EJB
	private UserDao userDao;
	
 
	private String nom;
	private String prenom;
	private String mail;
	private String password;
	private String nomSociete;
	private String grade;
 
	private User user=new User();
 	private User newUser=new User();
	private User logedUser;
	
	
	
	@PostConstruct
	public void init(){		 
		logedUser=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logedUser");		
	 
 	}
	
 		
	public void addNewCompte()
	{
		System.out.println("add new User");
			user.setGrade("admin");
			user.setAddedBy("root");
			userDao.createUser(user);	 

		System.out.println("END add  User");
		user=new User();
		newUser=new User();
		
	}
	
 
	public List<User> listeUser(){
		List<User> listeUser =new ArrayList<>();
		listeUser=userDao.getUser();
		return listeUser;
	}
	
	public void updateProfileUser(){
 
		userDao.updateCompteUser(logedUser);
	}
	
	//Getter and Setter
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

 

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}




	public User getLogedUser() {
		return logedUser;
	}




	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}






 
	 

 

 
	
	
}
