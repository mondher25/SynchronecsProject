package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CompartimentAffPlannerUserDao;
import entities.AffectationPlannerUser;
import entities.CompartimentAffPlannerUser;

@Stateless
public class CompartimentAffPlannerUserJPA implements CompartimentAffPlannerUserDao{
	
	@PersistenceContext(unitName="UP")
	EntityManager em;
	
	@Override
	public void AddCompByPlannerUser(CompartimentAffPlannerUser compAff) {
		em.merge(compAff);
		
	}

	@Override
	public List<CompartimentAffPlannerUser> comparByPlaUsr(String mail, Long idp) {
		List<CompartimentAffPlannerUser> listCom=new ArrayList<>();
		listCom=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE planner_id=:idp AND user_id=:mail ").setParameter("mail", mail).setParameter("idp", idp).getResultList();
		return listCom;
	}

}
