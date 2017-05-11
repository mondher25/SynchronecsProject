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
	public List<CompartimentAffPlannerUser> comparByPlaUsr(Long idUser, Long idp) {
		List<CompartimentAffPlannerUser> listCom=new ArrayList<>();
		listCom=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE planner_id=:idp AND user_id=:id ").setParameter("id", idUser).setParameter("idp", idp).getResultList();
		return listCom;
	}

	@Override
	public List<CompartimentAffPlannerUser> getUserByPlannerAndComAff(Long idp, Long idCom) {
		List<CompartimentAffPlannerUser> listeUserByPlannerAndCompAff=new ArrayList<>();
		listeUserByPlannerAndCompAff=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE planner_id=:idp AND compartiment_id=:idCom").setParameter("idp", idp).setParameter("idCom", idCom).getResultList();
		return listeUserByPlannerAndCompAff;
	}

	@Override
	public List<String> getUserById(String mail) {
		 
			List<String> listeUser=new ArrayList<>();
			listeUser=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE user_id=:idUser ").setParameter("idUser", mail).getResultList();
			return listeUser;
	}

	@Override
	public List<CompartimentAffPlannerUser> getAllCompartiment(Long idUser,Long idp) {
		 
			List<CompartimentAffPlannerUser> listeUser=new ArrayList<>();
			listeUser=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE planner_id=:idp AND user_id=:idUser").setParameter("idp", idp).setParameter("idUser", idUser).getResultList();
			return listeUser;
	 
	}

	@Override
	public void deletComp(Long idCom) {
		if (idCom != null)
		em.createNativeQuery("DELETE FROM CompartimentAffPlannerUser WHERE compartiment_id=:id").setParameter("id", idCom).executeUpdate();
		
	}

	@Override
	public void deletePlannerAffComp(Long id) {
		if(id != null)
		em.createNativeQuery("DELETE FROM CompartimentAffPlannerUser WHERE planner_id=:id").setParameter("id", id).executeUpdate();
		
	}

	@Override
	public List<CompartimentAffPlannerUser> getCompByPlanner(Long idp) {

		List<CompartimentAffPlannerUser> listeUser=new ArrayList<>();
		listeUser=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE planner_id=:idp").setParameter("idp", idp).getResultList();
		return listeUser;
	}

	@Override
	public List<CompartimentAffPlannerUser> comparByPublicPlanner(Long idUser) {
		List<CompartimentAffPlannerUser> liste=new ArrayList<>();
		liste=em.createQuery("SELECT c FROM CompartimentAffPlannerUser c WHERE user_id=:idUser ").setParameter("idUser", idUser).getResultList();
		return liste;
	}

}
