package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.UserDao;
import entities.User;

/**
 * Session Bean implementation class User
 */
@Stateless
public class UserJPA implements UserDao {

	@PersistenceContext(unitName="UP")
	EntityManager em;
	
 

@Override
public User login(String mail, String password) {
	User user =null ;
	try{
		user = (User) em.createQuery("SELECT u FROM User u WHERE mail=:mail AND password=:password").
				setParameter("mail", mail).
				setParameter("password", password).
				getSingleResult();
	}catch (Exception e) {
		return user;
	}

 
	return user;
}

@Override
public void createUser(User u) {
	em.persist(u);
	em.flush();
	
}

 

@Override
public User getUserById(Long id) {
	User user=em.find(User.class, id);
	return user;
}
@Override
public User getUserByMailId(String mail) {
	User user =null ;
	try{
		user = (User) em.createQuery("SELECT u FROM User u WHERE mail=:mail").
				setParameter("mail", mail).				 
				getSingleResult();
	}catch (Exception e) {
		return user;
	}

 
	return user;
	 
}


@Override
public List<User> ListeUserAffTache(String mail, String grade) {
	List<User> listUser=em.createQuery("SELECT u FROM User u WHERE mail=:mail AND grade=:grade").setParameter("mail", mail).setParameter("grade", grade).getResultList();
	return listUser;
}

@Override
public List<User> getUser() {
	List<User> listAllUser=new ArrayList<>();
	String gradeUser="user";
	listAllUser=em.createQuery("SELECT u FROM User u WHERE grade=:gradeUser ").setParameter("gradeUser", gradeUser).getResultList();
	
	return listAllUser;
}

@Override
public User getUserByGrade(){
	 String grade="admin";
	User user=null;
	try{
		user=(User)em.createQuery("SELECT u FROM User u WHERE grade=:grade").setParameter("grade", grade).getSingleResult();
		
	}catch(Exception e){
		return user;
	}
	return user;
	 
}

@Override
public void updateCompteUser(User u) {
	em.merge(u);
 
	
}

@Override
public List<User> getAllUserByGrade() {
	 String grade="user";
		List<User> user=new ArrayList<>();
		try{
			user=em.createQuery("SELECT u FROM User u WHERE grade=:grade").setParameter("grade", grade).getResultList();
			
		}catch(Exception e){
			return user;
		}
		return user;
}

 


}
