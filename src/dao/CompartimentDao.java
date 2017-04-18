package dao;

 

import java.util.List;
import javax.ejb.Local;
import entities.Compartiment;
 

@Local
public interface CompartimentDao {
	public void addCompartiment(Compartiment comp );
	public List<Compartiment> getListCompartimentByPlanner(Long id);
	public Compartiment getCompartimentById(Long id);
	public List<Compartiment> getListCompartimentByPlannerAndUser(Long idPlanner,String mail);
	public List<Compartiment> getAllCompartiment(Long idUser);
	public void removeCom(Compartiment com);

}
