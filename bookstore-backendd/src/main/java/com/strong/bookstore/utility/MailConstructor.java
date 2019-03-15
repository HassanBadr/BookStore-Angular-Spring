package com.strong.bookstore.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.strong.bookstore.domain.User;

@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		String message = "\nPlease use the following credentials to log in and edit your personal information."
				+ "\nUsername:"+user.getUsername()+"\nPassword:"+password;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Strong BookStore - New User");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		
		return email;
	}
	
	public SimpleMailMessage constructForgetNewPassword(User user, String password) {
		String message = "\nPlease use the following credentials to log in and edit your personal information."
				+ "\nUsername:"+user.getUsername()+"\nNew Password:"+password;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Strong BookStore - Forgeted Password");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		
		return email;
	}

}
