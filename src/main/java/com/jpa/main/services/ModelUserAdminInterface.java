package com.jpa.main.services;

import java.util.List;

import com.jpa.main.entity.UserAdmin;

public interface ModelUserAdminInterface {

public List<UserAdmin> getAllUserAdmin();
	
	public UserAdmin addUserAdmin(UserAdmin userAdmin);
	public UserAdmin getUserAdminById(String id);
	public void deleteUserAdmin(String id);
}
