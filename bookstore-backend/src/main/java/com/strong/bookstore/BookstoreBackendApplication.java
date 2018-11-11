package com.strong.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.strong.bookstore.config.SecurityUtility;
import com.strong.bookstore.domain.User;
import com.strong.bookstore.domain.security.Role;
import com.strong.bookstore.domain.security.UserRole;
import com.strong.bookstore.service.UserService;

@SpringBootApplication
public class BookstoreBackendApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreBackendApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		//user1
//		User user1 = new User();
//		user1.setFirstName("Hassan");
//		user1.setLastName("Mohammed");
//		user1.setUsername("strong");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//		user1.setEmail("hassan@gmail.com");
//		Set<UserRole> userRoles = new HashSet<>();
//		Role role1 = new Role();
//		role1.setRoleId(1);
//		role1.setName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//		
//		userService.createUser(user1, userRoles);
//		
//		userRoles.clear();
//		
//		//user2
//		User user2 = new User();
//		user2.setFirstName("Admin");
//		user2.setLastName("Admin");
//		user2.setUsername("admin");
//		user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
//		user2.setEmail("MAdmin@gmail.com");
//		Role role2 = new Role();
//		role2.setRoleId(0);
//		role2.setName("ROLE_ADMIN");
//		userRoles.add(new UserRole(user2, role2));
//		
//		userService.createUser(user2, userRoles);
//		
//		
//	}
}
