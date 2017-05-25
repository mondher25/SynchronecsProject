package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.SupPlannerDao;
import entities.SupPlanner;

@Stateless
public class SupPlannerJPA implements SupPlannerDao {

	@PersistenceContext(unitName="UP")
	EntityManager em;

	@Override
	public void addSuperviseur(SupPlanner sup) {
		em.persist(sup);
		
	}

	@Override
	public void deleteSupPlanner(Long id) {
		em.createNativeQuery("DELETE FROM SupPlanner WHERE planner_id=:id").setParameter("id", id).executeUpdate();
		
	}

	@Override
	public List<SupPlanner> getSupPlanner(Long idUser) {
		List<SupPlanner> liste=new ArrayList<>();
		liste=em.createQuery("SELECT s FROM SupPlanner s WHERE user_id=:id").setParameter("id", idUser).getResultList();
		return liste;
	}

	@Override
	public List<SupPlanner> getAllSupPlanner() {
		List<SupPlanner> liste=new ArrayList<>();
		liste=em.createQuery("SELECT s FROM SupPlanner s").getResultList();
		return liste;
	}
	
	
	
	
}
