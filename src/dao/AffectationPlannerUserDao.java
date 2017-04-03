package dao;

import java.util.List;

import javax.ejb.Local;

import entities.AffectationPlannerUser;
import entities.Planner;

@Local
public interface AffectationPlannerUserDao {

public void addAff(AffectationPlannerUser affPlaUser);
public List<AffectationPlannerUser> listPlannerByAffectation(String mail,String nomSociete);

}
