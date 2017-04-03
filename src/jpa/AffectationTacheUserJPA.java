package jpa;

 

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.AffectationTacheUserDao;
import entities.AffectationTacheUser;

@Stateless
public class AffectationTacheUserJPA implements AffectationTacheUserDao {
	
	@PersistenceContext(unitName="UP")
	EntityManager em;

 

	@Override
	public void addAffTache(AffectationTacheUser affTache) {
		em.persist(affTache);
		
	}
	
	
	

}
