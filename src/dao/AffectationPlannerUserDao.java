package dao;

import javax.ejb.Local;

import entities.AffectationPlannerUser;

@Local
public interface AffectationPlannerUserDao {

public void addAff(AffectationPlannerUser affPlaUser);

}
