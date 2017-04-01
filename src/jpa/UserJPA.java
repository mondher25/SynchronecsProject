package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.UserDao;
import entities.User;

/**
 * Session Bean implementation class Utilisateur
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
	
}

 

@Override
public User getUserByMailId(String mailId) {
	User user=em.find(User.class, mailId);
	return user;
}

@Override
public List<User> ListeUserAffTache(String mail, String grade) {
	List<User> listUser=em.createQuery("SELECT u FROM User u WHERE mail=:mail AND grade=:grade").setParameter("mail", mail).setParameter("grade", grade).getResultList();
	return listUser;
}

@Override
public List<User> getAllUser() {
	List<User> listAllUser=new ArrayList<>();
	String gradeUser="user";
	listAllUser=em.createQuery("SELECT u FROM User u WHERE grade =:grade").setParameter("grade", gradeUser).getResultList();
	
	return listAllUser;
}


}
