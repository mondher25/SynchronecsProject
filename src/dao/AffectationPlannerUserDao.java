package dao;

import java.util.List;

import javax.ejb.Local;

import entities.AffectationPlannerUser;
import entities.User;
 

@Local
public interface AffectationPlannerUserDao {

public void addAff(AffectationPlannerUser affPlaUser);
public List<AffectationPlannerUser> listPlannerByAffectation(String mail,String nomSociete);
public void getAllUserByPlannerAffectation(String mail,Long idp);
public void addDefaultUserAff(AffectationPlannerUser defaultAffUserPlanner);
public List<AffectationPlannerUser> getPlannerByNomSocieteAndEtat(boolean etat);
public List<AffectationPlannerUser> listPlannerByAffectationAndMailId(String mail);
public List<AffectationPlannerUser> getAllPlanner();
public List<AffectationPlannerUser> getUserByPlannerAff(Long idp);
public User getUserByMailId(String mail);
}
