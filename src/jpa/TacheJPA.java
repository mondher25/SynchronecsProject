package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.TacheDao;
import entities.Tache;

@Stateless
public class TacheJPA implements TacheDao {

	@PersistenceContext(unitName="UP")
	EntityManager entityManager;
	
	@Override
	public void addTache(Tache t) {
		entityManager.persist(t);
		
	}

	@Override
	public List<Tache> getTacheByCompartiment(Long id) {
		 List<Tache> listeTache=new ArrayList<>();
		 listeTache=entityManager.createQuery("SELECT t FROM Tache t WHERE compartiment_id = :compartiment_id").setParameter("compartiment_id", id).getResultList();
	 
		 return listeTache;
	}

	@Override
	public List<Tache> getTacheByCompartimentAndUser(Long idCom, String mail) {
		List<Tache> listeTacheComCp=new ArrayList<>();
		listeTacheComCp=entityManager.createQuery("SELECT t FROM Tache t WHERE compartiment_id=:compartiment AND user_mail_id=:user").setParameter("compartiment", idCom).setParameter("user", mail).getResultList();
		return listeTacheComCp;
	}

	@Override
	public void updateTache(Tache t) {
		entityManager.merge(t);
		
	}

	@Override
	public void removeTache(Long id) {
		Tache tache=findTacheById(id);
		entityManager.remove(tache);
		 
	}

	@Override
	public List<Tache> getAllTache(Long idUser) {
		List<Tache> listeTache=new ArrayList<>();
		listeTache=entityManager.createQuery("SELECT t FROM Tache t WHERE user_id=:id").setParameter("id", idUser).getResultList();
				return listeTache;
	}

	@Override
	public Tache findTacheById(Long id) {
		Tache tache=entityManager.find(Tache.class, id);
		return tache;
	}

	@Override
	public void remove(Tache tache) {
		entityManager.remove(entityManager.merge(tache));
		
	}

 

 

}
