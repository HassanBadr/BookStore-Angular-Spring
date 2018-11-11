package com.strong.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.strong.bookstore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
