package jpa;

 

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.TacheUPCDao;
import entities.TacheUPC;

@Stateless
public class TacheUPCJPA implements TacheUPCDao {
	
	@PersistenceContext(unitName="UP")
	EntityManager em;

 

	@Override
	public void addAffTache(TacheUPC affTache) {
		em.persist(affTache);
		
	}
	
	
	

}
