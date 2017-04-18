package dao;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserDao {
	
	public void createUser(User u);
	public User login(String mail,String password);
 	public User getUserByMailId(String mail); 
 	public User getUserById(Long id); 
	public List<User> ListeUserAffTache(String mail,String grade);
	public List<User> getUser();
}
