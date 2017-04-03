package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.AffectationPlannerUserDao;
import entities.AffectationPlannerUser;
import entities.Planner;

@Stateless
public class AffectationPlannerUserJPA implements AffectationPlannerUserDao{

	@PersistenceContext(unitName = "UP")
	EntityManager em;
	
	@Override
	public void addAff(AffectationPlannerUser affPlaUser) {
		em.merge(affPlaUser);
		
	}

 

	@Override
	public List<AffectationPlannerUser> listPlannerByAffectation(String mail,String nomSociete) {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:user AND nomSociete=:nomSociete AND planner_id=planner").setParameter("user", mail).setParameter("nomSociete", nomSociete).getResultList();
		return listPlanner;
	}


}
