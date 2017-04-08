package jpa;

 

import java.util.ArrayList;
import java.util.List;

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
	public void addAffTache(TacheUPC tacheUPC) {
		em.merge(tacheUPC);
		
	}



	@Override
	public List<TacheUPC> getTacheByPlannerCompartimentUser(Long idp, Long idCom, Long idTache, String mail) {
			List <TacheUPC> listeTache=new ArrayList<>();
			listeTache=em.createQuery("SELECT t FROM TacheUPC t WHERE planner_id=:idp AND compartiment_id=:idCom AND user_id=:mail AND tache_id=:idTache").
			setParameter("idp", idp).setParameter("idCom", idCom).setParameter("mail", mail).setParameter("idTache", idTache).getResultList();
			return listeTache;
					
					
					
	}



	@Override
	public List<TacheUPC> getTacheByPlannerCompartiment(Long idp, Long idCom) {
		List <TacheUPC> listeTache=new ArrayList<>();
		listeTache=em.createQuery("SELECT t FROM TacheUPC t WHERE planner_id=:idp AND compartiment_id=:idCom ").
		setParameter("idp", idp).setParameter("idCom", idCom).getResultList();
		return listeTache;
	}



	@Override
	public List<TacheUPC> getTacheById(Long idTache) {
		List<TacheUPC> listeTache=new ArrayList<>();
		listeTache=em.createQuery("SELECT t FROM TacheUPC t WHERE idTache=:id").setParameter("id", idTache).getResultList();
		return listeTache;
	}



	@Override
	public void delete(Long id) {
//		TacheUPC tache=em.find(TacheUPC.class, id);
		em.createNativeQuery("DELETE FROM TacheUPC WHERE tache_id=:id").setParameter("id", id).executeUpdate();
		
	}



	@Override
	public TacheUPC findTacheUPCById(Long id) {
		TacheUPC tacheUPC=em.find(TacheUPC.class, id);
		return tacheUPC;
	}



//	@Override
//	public List<TacheUPC> getAllTache() {
//		List<TacheUPC> liste=new ArrayList<>();
//		liste=em.createQuery("SELECT t FROM TacheUPC t").getResultList();
//		return liste;
//	}
	
	
	

}
