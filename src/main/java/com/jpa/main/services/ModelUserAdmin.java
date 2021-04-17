package com.jpa.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.main.entity.UserAdmin;
import com.jpa.main.repository.UserAdminRepository;

@Service
public class ModelUserAdmin implements ModelUserAdminInterface{

	@Autowired
	UserAdminRepository userAdminRepo;
	
	@Override
	public List<UserAdmin> getAllUserAdmin() {
		// TODO Auto-generated method stub
		return (List<UserAdmin>) this.userAdminRepo.findAll();
	}

	@Override
	public UserAdmin addUserAdmin(UserAdmin userAdmin) {
		// TODO Auto-generated method stub
		return this.userAdminRepo.save(userAdmin);
	}

	@Override
	public UserAdmin getUserAdminById(String id) {
		// TODO Auto-generated method stub
		return this.userAdminRepo.findById(Long.parseLong(id)).get();
	}

	@Override
	public void deleteUserAdmin(String id) {
		// TODO Auto-generated method stub
		this.userAdminRepo.deleteById(Long.parseLong(id));
	}

}
