package dao;

import javax.ejb.Local;

import entities.Compte;
import entities.Groupe;

 

@Local
public interface GroupeDao {

	public void addGroupe(Groupe grp);
	public Groupe getGroupeById(Long id);
}
