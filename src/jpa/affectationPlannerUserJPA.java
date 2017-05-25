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
		boolean etat=false;
//		String etatsup="non";
//		liste=em.createQuery("SELECT DISTINCT(a.planner) FROM   AffectationPlannerUser a WHERE a.etat=:etat").setParameter("etat", etat).getResultList();
 	liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:id  AND etat=:etat").setParameter("id", idUser).setParameter("etat", etat).getResultList();

//		liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE user_id=:id AND etat=:etat").setParameter("id", idUser).setParameter("etat", etat).getResultList();
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
// 		String sup="non";
//		listPlanner=em.createNativeQuery("select DISTINCT planner_id  FROM AffectationPlannerUser WHERE etat=:etat").setParameter("etat",etat).getResultList();
 		listPlanner=em.createQuery("SELECT DISTINCT(a.planner) FROM AffectationPlannerUser a WHERE a.etat=:etat ").setParameter("etat",etat).getResultList();
		
//		listPlanner=em.createQuery("SELECT DISTINCT(a.planner_id)  FROM AffectationPlannerUser a WHERE etat=:etat").setParameter("etat",etat).getResultList();

		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getSuperPlanner(Long id) {
	 
		List<AffectationPlannerUser> liste=new ArrayList<>();
//		Set set = new HashSet();
//		List<AffectationPlannerUser> liste2=new ArrayList<>();
//		String etatsup="oui";
 		liste=em.createQuery("SELECT DISTINCT(a.planner) FROM AffectationPlannerUser a WHERE a.superviseur_id=:id ").setParameter("id", id).getResultList();

// 		liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE superviseur_id=:id AND user_id<>superviseur_id ").setParameter("id", id).getResultList();
//		liste=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE superviseur_id=:id  ").setParameter("id", id).getResultList();
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



	@Override
	public List<Long> getSuperviseurPlanner(Long idp) {
		List<Long> listPlanner=new ArrayList<>();
		listPlanner=em.createQuery("SELECT a.superviseur_id FROM AffectationPlannerUser a WHERE planner_id=:idp ").setParameter("idp", idp).getResultList();
		return listPlanner;
		 
	}


	public List<AffectationPlannerUser> getAllPlannerAdministration() {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		String etatsup="non";
		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE etatsupervise=:etatsup ").setParameter("etatsup", etatsup).getResultList();

		
		
		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getPublicPlannerSuper() {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		boolean etat=true;
 		String sup="oui";
//		listPlanner=em.createNativeQuery("select DISTINCT planner_id  FROM AffectationPlannerUser WHERE etat=:etat").setParameter("etat",etat).getResultList();
 		listPlanner=em.createQuery("SELECT a FROM AffectationPlannerUser a WHERE etat=:etat AND etatsupervise=:sup ").setParameter("etat",etat).setParameter("sup", sup).getResultList();
		
//		listPlanner=em.createQuery("SELECT DISTINCT(a.planner_id)  FROM AffectationPlannerUser a WHERE etat=:etat").setParameter("etat",etat).getResultList();

		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getAllPlannerAdmin() {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();
		 
		listPlanner=em.createQuery("SELECT DISTINCT(a.planner) FROM AffectationPlannerUser a  ").getResultList();

		
		
		return listPlanner;
	}



	@Override
	public List<AffectationPlannerUser> getAllPlannerUser(Long idUser) {
		List<AffectationPlannerUser> listPlanner=new ArrayList<>();		 
		listPlanner=em.createQuery("SELECT DISTINCT(a.planner) FROM AffectationPlannerUser a WHERE a.user_id=:id  ").setParameter("id", idUser).getResultList();
		return listPlanner;
	}
 


 

}
