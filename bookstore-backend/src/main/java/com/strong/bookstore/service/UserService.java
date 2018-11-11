package com.strong.bookstore.service;

import java.util.Set;

import com.strong.bookstore.domain.User;
import com.strong.bookstore.domain.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);

}
