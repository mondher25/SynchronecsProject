package jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.AffectationPlannerUserDao;
import entities.AffectationPlannerUser;

@Stateless
public class affectationPlannerUserJPA implements AffectationPlannerUserDao{

	@Override
	public void addAff(AffectationPlannerUser affPlaUser) {
		em.persist(affPlaUser);
		
	}

	@PersistenceContext(unitName="UP")
	EntityManager em;
}
