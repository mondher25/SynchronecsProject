package dao;

import java.util.List;
import javax.ejb.Local;
import entities.CompartimentAffPlannerUser;

@Local
public interface CompartimentAffPlannerUserDao {
	public void AddCompByPlannerUser(CompartimentAffPlannerUser compAff);
	public List<CompartimentAffPlannerUser> comparByPlaUsr(Long idUser,Long idp);
	public List<CompartimentAffPlannerUser> getUserByPlannerAndComAff(Long idp,Long idCom);
	public List<CompartimentAffPlannerUser> getCompByPlanner (Long idp);
	public List<String> getUserById(String mail);
	public List<CompartimentAffPlannerUser> getAllCompartiment(Long idUser,Long idp);
	public void deletComp(Long idCom);
	public void deletePlannerAffComp(Long id);
	public List<CompartimentAffPlannerUser> comparByPublicPlanner(Long idUser);
}
