package dao;

import java.util.List;

import javax.ejb.Local;

import entities.AffectationPlannerUser;
import entities.CompartimentAffPlannerUser;

@Local
public interface CompartimentAffPlannerUserDao {
	public void AddCompByPlannerUser(CompartimentAffPlannerUser compAff);
	public List<CompartimentAffPlannerUser> comparByPlaUsr(String mail,Long idp);
	public List<String> getUserByPlannerAndComAff(Long idp,Long idCom);
	public List<String> getUserById(String mail);

}
