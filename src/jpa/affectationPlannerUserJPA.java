package jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.AffectationPlannerUserDao;
import entities.AffectationPlannerUser;

@Stateless
public class AffectationPlannerUserJPA implements AffectationPlannerUserDao{

	@PersistenceContext(unitName="UP")
	EntityManager em;
	
	@Override
	public void addAff(AffectationPlannerUser affPlaUser) {
		em.persist(affPlaUser);
		
	}


}
