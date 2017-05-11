package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CompartimentDao;
import entities.Compartiment;
import entities.Planner;

@Stateless
public class CompartimentJPA implements CompartimentDao {

	@PersistenceContext(unitName = "UP")
	EntityManager em;

	@Override
	public void addCompartiment(Compartiment c) {
		em.persist(c);
		em.flush();
	}

	@Override
	public Compartiment getCompartimentById(Long id) {
		Compartiment cp = em.find(Compartiment.class, id);
		return cp;
	}

	@Override
	public List<Compartiment> getListCompartimentByPlanner(Long planner_id) {
		List<Compartiment> listeCom = new ArrayList<Compartiment>();
		listeCom = em.createQuery("SELECT c FROM Compartiment c WHERE planner_id=:planner_id")
				.setParameter("planner_id", planner_id).getResultList();
		return listeCom;
	}

	@Override
	public List<Compartiment> getListCompartimentByPlannerAndUser(Long idPlanner, String mail) {
		List<Compartiment> listCompPlCp = new ArrayList<>();
		listCompPlCp = em.createQuery("SELECT c FROM Compartiment c WHERE planner_id=:planner AND user_mail_id=:user")
				.setParameter("planner", idPlanner).setParameter("user", mail).getResultList();
		return listCompPlCp;
	}

	@Override
	public List<Compartiment> getAllCompartiment(Long idUser) {
		List<Compartiment> listCompPlCp = new ArrayList<>();
		listCompPlCp = em.createQuery("SELECT c FROM Compartiment c WHERE user_id=:id ")
				.setParameter("id", idUser).getResultList();
		return listCompPlCp;
	}

	@Override
	public void removeCom(Compartiment com) {
		em.remove(em.merge(com));
		
	}

	@Override
	public void deletePlannerCompartiment(Long id) {
		if(id !=null)
		em.createNativeQuery("DELETE FROM Compartiment WHERE planner_id=:id").setParameter("id", id).executeUpdate();
		
	}

	@Override
	public void updateCompartiment(Compartiment comp) {
//		em.createNativeQuery("UPDATE Compartiment c SET   nomCompartiment=:nomCom").setParameter("nomCom", nomCom).executeUpdate();
	em.merge(comp);	
	 
	}
}
