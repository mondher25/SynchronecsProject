package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CompteDao;
import dao.GroupeDao;
import dao.UserDao;
import entities.Compte;
import entities.Groupe;
import entities.User;

@ManagedBean(name="userBean")
@ViewScoped
public class userBean {

	
	@EJB
	private UserDao userDao;
	
	@EJB
	private CompteDao compteDao;
	
	@EJB
	private GroupeDao groupeDao;

	private String nom;
	private String prenom;
	private String mail;
	private String password;
	private String idUser;
	
	private User user=new User();
	private Compte connectedUser;
	private Groupe groupe;
	
	@PostConstruct
	public void init(){
		idUser=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idUser");
		connectedUser=compteDao.getCompteById(Long.parseLong(idUser));
		groupe=groupeDao.getGroupeById(Long.parseLong(idUser));
	}
	
	public void addUser()
	{
		System.out.println("add new User");
		
		user.setCompte(connectedUser);
		user.setGroupe(groupe);
		user.setGrade("user");
		userDao.createUser(user);
		System.out.println("END add  User");
		user=new User();
	}
	
	public List<User> listeUserByCompteConnecter()
	{
		List<User> listUser=new ArrayList<>();
		listUser=userDao.listeUser(connectedUser.getId());
		return listUser;
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

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	
}
