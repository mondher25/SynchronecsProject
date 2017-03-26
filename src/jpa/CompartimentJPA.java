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
	
	@PersistenceContext(unitName="UP")
	EntityManager em;

	@Override
	public void addCompartiment(Compartiment c) {
		 em.persist(c);		
	}

 

	@Override
	public List<Compartiment> getListCompartimentByPlanner(Long planner_id) {
		List<Compartiment> listeCom = new ArrayList<Compartiment>();
	listeCom=em.createQuery("SELECT c FROM Compartiment c WHERE planner_id=:planner_id").setParameter("planner_id", planner_id).getResultList();
		return listeCom;
	}



	@Override
	public Compartiment getCompartimentById(Long id) {
		Compartiment cp=em.find(Compartiment.class, id);	 
		return cp;
	}
}
