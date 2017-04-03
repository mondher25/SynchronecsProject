package dao;

import java.util.List;

import javax.ejb.Local;

import entities.AffectationPlannerUser;
 

@Local
public interface AffectationPlannerUserDao {

public void addAff(AffectationPlannerUser affPlaUser);
public List<AffectationPlannerUser> listPlannerByAffectation(String mail,String nomSociete);
public void getAllUserByPlannerAffectation(String mail,Long idp);
public void addDefaultUserAff(AffectationPlannerUser defaultAffUserPlanner);
public void getPlannerByNomSociet(String nomSociete);

}
