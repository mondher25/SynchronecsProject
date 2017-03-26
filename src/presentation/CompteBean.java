package presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import dao.CompteDao;
import entities.Compte;

@ManagedBean(name="cp")
@RequestScoped
public class CompteBean {

	
	@EJB
	private CompteDao compteDao;
	
	private String nom;
	private String prenom;
	private String mail;
	private String nomSociete;
	private String password;
	private Compte compte =new Compte();
	
	
	
	public void addNewCompte()
	{
		System.out.println("start add Compte");
		compteDao.creeCompte(compte);
		new Compte();
		System.out.println(" end Add Compte ");
	}
	
	
	//Getter and Setter
	
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


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
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
