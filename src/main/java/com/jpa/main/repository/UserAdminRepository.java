package com.jpa.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.jpa.main.entity.UserAdmin;


public interface UserAdminRepository extends CrudRepository<UserAdmin, Long>{
	public UserAdmin findByUsername(String username);

}
