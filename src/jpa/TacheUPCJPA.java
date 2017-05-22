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



	@Override
	public void deleteComp(Long id) {
		if( id != null)
		em.createNativeQuery("DELETE FROM TacheUPC  WHERE compartiment_id=:id").setParameter("id", id).executeUpdate();
		
	}



	@Override
	public void deletePlannerAffTacheUPC(Long id) {
		if (id !=null)
		em.createNativeQuery("DELETE FROM TacheUPC WHERE planner_id=:id" ).setParameter("id", id).executeUpdate();
		
	}



	@Override
	public List<TacheUPC> getTacheByUser(Long idp, Long idCom) {
		List<TacheUPC> liste=new ArrayList<>();
		String userGrade="user";
		liste=em.createQuery("SELECT t FROM TacheUPC t WHERE planner_id=:id AND compartiment_id=:idC AND userGrade=:userGrade").setParameter("id", idp).setParameter("idC", idCom).setParameter("userGrade", userGrade).getResultList();	
		return liste;
	}



	@Override
	public List<TacheUPC> getTacheByDate(Long idUser) {
		List <TacheUPC> liste=new ArrayList<>();
		 
		liste=em.createNativeQuery("SELECT nomTache FROM TacheUPC WHERE user_id=:id AND dateEcheance < now()").
		setParameter("id", idUser). getResultList();
		return liste;
	}



	@Override
	public List<TacheUPC> getTacheNonCommence(Long idUser) {
		List <TacheUPC> liste=new ArrayList<>();
		String etat="non-commencé";
		liste=em.createQuery("SELECT t FROM TacheUPC t WHERE etat=:etat AND user_id=:id ").
		setParameter("id", idUser).setParameter("etat", etat). getResultList();
		return liste;
	}



	@Override
	public List<TacheUPC> getTacheTermine(Long idUser) {
		List <TacheUPC> liste=new ArrayList<>();
		String etat="terminé";
		liste=em.createQuery("SELECT t FROM TacheUPC t WHERE etat=:etat AND user_id=:id ").
		setParameter("id", idUser).setParameter("etat", etat). getResultList();
		return liste;
	}



	@Override
	public List<TacheUPC> getTacheEnCour(Long idUser) {
		List <TacheUPC> liste=new ArrayList<>();
		String etat="en-cours";
		liste=em.createQuery("SELECT t FROM TacheUPC t WHERE etat=:etat AND user_id=:id ").
		setParameter("id", idUser).setParameter("etat", etat). getResultList();
		return liste;
	}



	@Override
	public void updateTacheUPC(Long idTache, String etat) {
		em.createNativeQuery("UPDATE TacheUPC set etat=:etat WHERE tache_id=:id").setParameter("etat", etat).setParameter("id", idTache).getResultList();
		
	}

}
