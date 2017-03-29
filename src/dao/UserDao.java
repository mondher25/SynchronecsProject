package dao;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserDao {
	
	public void createUser(User u);
	public User login(String mail,String password);
	public List<User> listeUser(Long id);
	public User getUserById(Long id);
}
