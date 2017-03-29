package jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.GroupeDao;
import entities.Compte;
import entities.Groupe;

@Stateless
public class GroupeJPA implements GroupeDao{

	@PersistenceContext(unitName="UP")
	EntityManager em;

	@Override
	public void addGroupe(Groupe grp){ 
		em.persist(grp);
		
	}

	@Override
	public Groupe getGroupeById(Long id) {
		Groupe cpr=new Groupe();
		cpr=em.find(Groupe.class, id);
		return cpr;
	}
	
 
	

}
