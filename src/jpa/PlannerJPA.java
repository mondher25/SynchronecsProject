package jpa;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.PlannerDao;
import entities.Planner;
 

/**
 * Session Bean implementation class Plan
 */
@Stateless
public class PlannerJPA implements PlannerDao {

 @PersistenceContext(unitName="UP")
 EntityManager entityManager;

@Override
public void AddPlanner(Planner planner) {
 
	entityManager.persist(planner);
	
}

@Override
public List<Planner> getAllPlanner() {
	List<Planner> list = new ArrayList<Planner>();
	list = entityManager.createQuery("SELECT  p FROM Planner p" ).getResultList();
	return list;
}

@Override
public Planner getPlannerById(Long id) {
	
	
	Planner p=entityManager.find(Planner.class, id);	 
	return p;
}

@Override
public List<Planner> getAllPlannerByMailId(String mail) {
	List <Planner> listePlanner=new ArrayList<>();
	listePlanner=entityManager.createQuery("SELECT p FROM Planner p WHERE user_mail_id= :mail ").setParameter("mail", mail).getResultList();
	return listePlanner;
}

@Override
public List<Planner> getAllPlannerByMailAndnomSociete(String mail, String nomSociete) {
	List <Planner> listePlanner=new ArrayList<>();
	listePlanner=entityManager.createQuery("SELECT p FROM Planner p WHERE user_mail_id=:mail AND nomSociete=:nomSociete").setParameter("mail", mail).setParameter("nomSociete", nomSociete).getResultList();
	return listePlanner;
}

@Override
public List<Planner> getAllPlannerById(Long id) {
	List <Planner> listePlanner=new ArrayList<>();
	listePlanner=entityManager.createQuery("SELECT p FROM Planner p WHERE user_id= :id ").setParameter("id", id).getResultList();
	return listePlanner;
}

@Override
public void remove(Planner planner) {
	entityManager.remove(entityManager.merge(planner));
	
}

@Override
public void updatePlanner(Planner planner) {
	entityManager.merge(planner);
	
}
}
  

 


