package com.Spring.RestAndMicroServices;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Spring.RestAndMicroServices.Entity.User;
import com.Spring.RestAndMicroServices.Entity.UserNotFoundException;
import com.Spring.RestAndMicroServices.Service.UserDAOService;

@RestController
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired
	private UserDAOService userDAO;
	//Get Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userDAO.findAll();
	}
	//Get specific user with ID
	@GetMapping("/users/{Id}")
	public User getUser(@PathVariable Integer Id) {
		User savedUser=userDAO.findOne(Id);
		if(savedUser==null) {
			throw new UserNotFoundException("User Id: "+Id+" Not Found");
		}
		return userDAO.findOne(Id);
		
		
	}
	
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> getUser(@Valid @RequestBody User user) {
		User savedUser=userDAO.save(user);
		
		//set the location uri for the user that is created
		//localhost:7070/users/{id}         /users/savedUser.getId()
		//use ServeletUriComponentBuilder and ResponseEntity for this
		
		
	URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/get/user/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
	
	
	return ResponseEntity.created(location).build();
	
		
		
	}
	
	
	//Get specific user with ID
		@DeleteMapping("/users/{Id}")
		public ResponseEntity<Object> deleteUser(@PathVariable Integer Id) {
			User savedUser=userDAO.deleteById(Id);
			if(savedUser==null) {
				throw new UserNotFoundException("User Id: "+Id+" Not Found");
			}
			
			return ResponseEntity.ok().build();
			
			
		
			
		}
		
		@GetMapping("/users/{Id}/hateoas")
		public EntityModel<User> getUserHateOas(@PathVariable Integer Id) {
			User savedUser=userDAO.findOne(Id);
			if(savedUser==null) {
				throw new UserNotFoundException("User Id: "+Id+" Not Found");
			}
			WebMvcLinkBuilder linkToUsers=linkTo(methodOn(this.getClass()).getAllUsers());
			EntityModel<User> model=EntityModel.of(savedUser);
			model.add(linkToUsers.withRel("all_users"));
			return model;
			
			
		}	
@Autowired
 private MessageSource messageSource;

		@GetMapping("/greetings")
		public String greetings(
			@RequestHeader(name="Accept-Language", required=false ) Locale Locale){
				return messageSource.getMessage("greetings.message", null, Locale);
			}
		
}
