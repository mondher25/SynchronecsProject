package presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.CompteDao;
import dao.GroupeDao;
import dao.UserDao;
import entities.Compte;
import entities.Groupe;
import entities.User;

@ManagedBean(name = "cp")
@RequestScoped
public class CompteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private CompteDao compteDao;
	
	@EJB
	private GroupeDao groupeDao;

	@EJB
	private UserDao userDao;
	
	private String nom;
	private String prenom;
	private String mail;
	private String nomSociete;
	private String password;
	private Compte compteUser = new Compte();
	private Groupe groupe=new Groupe();

	public void addNewCompte() {
		System.out.println("start add Compte");
		//
		compteDao.creeCompte(compteUser);
		//
		Groupe groupe=new Groupe();
		groupe.setCompte(compteUser);
		groupe.setNom(compteUser.getNomSociete());
		groupeDao.addGroupe(groupe);
		//
		User user = new User();
		user.setGrade("super");
		user.setMail(compteUser.getMail());
		user.setNom(compteUser.getNom());
		user.setPassword(compteUser.getPassword());
		user.setPrenom(compteUser.getPrenom());
		user.setCompte(compteUser);
		user.setGroupe(groupe);
		userDao.createUser(user);
		
		
		new Compte();
		System.out.println(" end Add Compte ");
		
	}

	// Getter and Setter

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

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public CompteDao getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}

 
	public Compte getCompteUser() {
		return compteUser;
	}

	public void setCompteUser(Compte compteUser) {
		this.compteUser = compteUser;
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

	public GroupeDao getGroupeDao() {
		return groupeDao;
	}

	public void setGroupeDao(GroupeDao groupeDao) {
		this.groupeDao = groupeDao;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	
	
}
