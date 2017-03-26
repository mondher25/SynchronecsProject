package jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CompteDao;
import entities.Compte;
 

@Stateless
public class CompteJPA implements CompteDao{

	@PersistenceContext(unitName="UP")
	EntityManager em;
	
	@Override
	public void creeCompte(Compte cp) {
		em.persist(cp);
		
	}

	@Override
	public Compte login(String mail, String password) {
		
		Compte cp = null;
		try {
			cp = (Compte) em.createQuery("SELECT c FROM Compte as c WHERE mail=:mail AND password=:password").
					setParameter("mail", mail).
					setParameter("password", password).
					getSingleResult();

		} catch (Exception e) {
			return cp;
		}  
		return cp;
 
	}

}
