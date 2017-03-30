package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CompteDao;
 
import dao.UserDao;
import entities.Compte;
 
import entities.User;

@ManagedBean(name="userBean")
@ViewScoped
public class userBean {

	
	@EJB
	private UserDao userDao;
	
	@EJB
	private CompteDao compteDao;
	
 

	private String nom;
	private String prenom;
	private String mail;
	private String password;
	private String nomSociete;
	private String grade;
	private String idUser;
	private String gradeHidden;
 
	
	private User user=new User();
	private Compte connectedUser;
	private User newUser=new User();
 
	
	@PostConstruct
	public void init(){
		 
		mail=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mail");
		grade=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("grade");
		
		  
 	}
	
	public void addNewCompte()
	{
		System.out.println("add new User");
		
			 
			user.setGrade("admin");
			user.setId((long) LoginBean.counterId());
			userDao.createUser(user);
			 
 
			
			 
		System.out.println("END add  User");
		user=new User();
		newUser=new User();
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

	public CompteDao getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Compte getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Compte connectedUser) {
		this.connectedUser = connectedUser;
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

	public String getGradeHidden() {
		return gradeHidden;
	}

	public void setGradeHidden(String gradeHidden) {
		this.gradeHidden = gradeHidden;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

 

 

 
	
	
}
