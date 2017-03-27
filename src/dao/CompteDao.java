package dao;

import javax.ejb.Local;

import entities.Compte;
 

@Local
public interface CompteDao {

	public void creeCompte(Compte cp);
	public Compte login(String mail,String password);
}
