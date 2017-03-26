package dao;

import javax.ejb.Local;

import entities.Compte;
import entities.User;

@Local
public interface CompteDao {

	public void creeCompte(Compte cp);
	public Compte login(String mail,String password);
}
