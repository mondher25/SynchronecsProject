package jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.AffectationPlannerUserDao;
import entities.AffectationPlannerUser;
import entities.User;
 

@Stateless
public class AffectationPlannerUserJPA implements AffectationPlannerUserDao{

	@PersistenceContext(unitName = "UP")
	EntityManager em;
	
	@Override
	public void addAff(AffectationPlannerUser affPlaUser) {
		em.merge(affPlaUser);
		em.flush();
		
	}

 

	@Override
	public List<AffectationPlannerUser> listPlannerByAffectation(String mail,String nomSociete) {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:user AND nomSociete=:nomSociete ").setParameter("user", mail).setParameter("nomSociete", nomSociete).getResultList();
		return listPlanner;
	}



	@Override
	public void getAllUserByPlannerAffectation(String mail, Long idp) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addDefaultUserAff(AffectationPlannerUser defaultAffUserPlanner) {
		em.persist(defaultAffUserPlanner);
		em.flush();
		
	}



 



	@Override
	public List<AffectationPlannerUser> getPlannerByNomSocieteAndEtat(boolean etat) {
		List <AffectationPlannerUser> allAffUserPlanner=new ArrayList<>();
		allAffUserPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE  etat=:etat").setParameter("etat", etat).getResultList();
		return allAffUserPlanner;
	}



	@Override
	public List<AffectationPlannerUser> listPlannerByAffectationAndMailId(String mail) {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:user ").setParameter("user", mail).getResultList();
		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getAllPlanner(Long idUser) {
		List<AffectationPlannerUser> liste=new ArrayList<>();
		liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:id").setParameter("id", idUser).getResultList();
		return liste;
	}



 


	@Override
	public List<AffectationPlannerUser> getUserByPlannerAff(Long idp) {
		List<AffectationPlannerUser> listeUserByPlannerAff=new ArrayList<>();
		listeUserByPlannerAff=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE planner_id=:idp").setParameter("idp", idp).getResultList();
		return listeUserByPlannerAff;
	}



	@Override
	public User getUserByMailId(String mail) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<AffectationPlannerUser> listPlannerByAffectationAndId(Long id) {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:user ").setParameter("user", id).getResultList();
		return listPlanner;
	}



	@Override
	public void deletePlannerAffUser(Long id) {
		if (id != null)
		em.createNativeQuery("DELETE FROM AffectationPlannerUser WHERE planner_id=:id" ).setParameter("id", id).executeUpdate();
		
	}



	@Override
	public List<AffectationPlannerUser> getPublicPlanner() {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		boolean etat=true;
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE etat=:etat ").setParameter("etat",etat).getResultList();
		
		
		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getSuperPlanner(Long id) {
	 
		List<AffectationPlannerUser> liste=new ArrayList<>();
//		Set set = new HashSet();
//		List<AffectationPlannerUser> liste2=new ArrayList<>();
		liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE superviseur_id=:id ").setParameter("id", id).getResultList();
	
//		set.addAll(liste);
//		liste.clear();
//		liste.addAll(set);
//		for(int i = 0; i < liste.size(); i++){
// 			AffectationPlannerUser o=liste.get(i) ;
// 			if(!liste2.contains(o)) 
// 				liste2.add(o);	 			 
// 			
// 		}
//		
		return liste;
	}



 


 

}
