package dao;

import java.util.List;

import javax.ejb.Local;

import entities.CompartimentAffPlannerUser;

@Local
public interface CompartimentAffPlannerUserDao {
	public void AddCompByPlannerUser(CompartimentAffPlannerUser compAff);
	public List<CompartimentAffPlannerUser> comparByPlaUsr(String mail,Long idp);

}
