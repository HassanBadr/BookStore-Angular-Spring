package com.strong.bookstore.resource;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.strong.bookstore.config.SecurityConfig;
import com.strong.bookstore.config.SecurityUtility;
import com.strong.bookstore.domain.User;
import com.strong.bookstore.domain.security.Role;
import com.strong.bookstore.domain.security.UserRole;
import com.strong.bookstore.service.UserService;
import com.strong.bookstore.utility.MailConstructor;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public ResponseEntity newUserPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {

		String username = mapper.get("username");
		String userEmail = mapper.get("email");

		if (userService.findByUsername(username) != null) {
			return new ResponseEntity("usernameExists", HttpStatus.BAD_REQUEST);
		}

		if (userService.findByEmail(userEmail) != null) {
			return new ResponseEntity("emailExists", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);

		String password = SecurityUtility.randomPassword();

		String encriptedPassword = SecurityUtility.passwordEncoder().encode(password);

		user.setPassword(encriptedPassword);

		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role));

		userService.createUser(user, userRoles);

		SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(email);

		return new ResponseEntity("User Added Successfully!", HttpStatus.OK);

	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ResponseEntity forgetPasswordPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {
		String userEmail = mapper.get("email");

		User user = userService.findByEmail(userEmail);

		if (user == null) {
			return new ResponseEntity("emailNotExists", HttpStatus.BAD_REQUEST);
		}

		String password = SecurityUtility.randomPassword();
		String encriptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encriptedPassword);

		userService.save(user);

		SimpleMailMessage newEmail = mailConstructor.constructForgetNewPassword(user, password);
		mailSender.send(newEmail);

		return new ResponseEntity("Email Sent", HttpStatus.OK);

	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public ResponseEntity profileInfo(@RequestBody HashMap<String, Object> mapper) throws Exception {

		int id = (Integer) mapper.get("id");
		String email = (String) mapper.get("email");
		String username = (String) mapper.get("username");
		String firstName = (String) mapper.get("firstName");
		String lastName = (String) mapper.get("lastName");
		String newPassword = (String) mapper.get("newPassword");
		String currentPassword = (String) mapper.get("currentPassword");

		User currentUser = userService.findById(Long.valueOf(id));

		if (currentUser == null) {
			throw new Exception("User not found");
		}

		if (userService.findByEmail(email) != null) {
			if (userService.findByEmail(email).getId() != currentUser.getId()) {
				return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
			}
		}

		if (userService.findByUsername(username) != null) {
			if (userService.findByUsername(username).getId() != currentUser.getId()) {
				return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
			}
		}

		SecurityConfig securityConfig = new SecurityConfig();

		if(currentPassword !=null) {
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			if (passwordEncoder.matches(currentPassword, dbPassword)) {
				if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
					currentUser.setPassword(passwordEncoder.encode(newPassword));
				}
				currentUser.setEmail(email);
			} else {
				return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
			}
		}
		
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setUsername(username);
		

		userService.save(currentUser);

		return new ResponseEntity("Update Success", HttpStatus.OK);

	}

	@RequestMapping("/getCurrentUser")
	public User getCurrentUser(Principal principal) {
		User user = new User();
		if (principal != null) {
			user = userService.findByUsername(principal.getName());
		}

		return user;
	}

}
