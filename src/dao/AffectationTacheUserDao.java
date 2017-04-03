package dao;

import javax.ejb.Local;

import entities.AffectationTacheUser;

@Local
public interface AffectationTacheUserDao {

	
	public void addAffTache(AffectationTacheUser affTache);
}
