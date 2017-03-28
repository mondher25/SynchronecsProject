package dao;

 

import java.util.List;
import javax.ejb.Local;
import entities.Compartiment;
 

@Local
public interface CompartimentDao {
	public void addCompartiment(Compartiment comp );
	public List<Compartiment> getListCompartimentByPlanner(Long id);
	public Compartiment getCompartimentById(Long id);
	public List<Compartiment> getListCompartimentByPlannerAndCompte(Long idPlanner,Long idUser);

}
