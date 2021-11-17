package com.Spring.RestAndMicroServices.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.Spring.RestAndMicroServices.Entity.User;
@Component
public class UserDAOService {

	public UserDAOService() {
		// TODO Auto-generated constructor stub
		
	}
	
	private static List<User> users=new ArrayList<User>();
	private Integer count=3;
	
static{
	users.add(new User(1, "Adam", new Date()));
	users.add(new User(2, "Jane", new Date()));
	users.add(new User(3, "Emily", new Date()));
}

//get List 

public List<User> findAll() {
	return users;

}

//add a user

public User save(User user) {
	if(user.getId()==null) {
		user.setId(++count);
	}
	users.add(user);
	return user;
	
}


//public Integer getId(String name) {
//Integer id=0;
//for(User user:users ) {
//	if(user.getName().equalsIgnoreCase(name)) {
//		id=user.getId();
//		System.out.println(user.getId()+": is the id");
//	}
//}
// return id;
//}

public User findOne(Integer Id) {
	User userDetails = null;
	for( User user: users) {
		if(user.getId()==Id) {
			userDetails=user;
			 
		}
		
		
	}
	return userDetails;
	
	
}

public User deleteById(Integer Id) {
	User user = null;
	Iterator<User> it= users.iterator();
	while(it.hasNext()) {
	 user=it.next();
	if(user.getId()==Id) {
		it.remove();
		return user;
	}
	}
	return null;
	
	
	
	
	
}
}
