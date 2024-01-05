package com.UserService.ServiceImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserService.Dao.UsersRepo;
import com.UserService.Exceptions.ResourceNotFoundException;
import com.UserService.Model.Users;
import com.UserService.Service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired 
	private UsersRepo repo;
	
	@Override
	public List<Users> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public String addUsers(Users users) {
	try {
		Users  users2=Users.builder().name(users.getName()).middelName(users.getMiddelName()).sirName(users.getSirName()).mobile(users.getMobile()).address(users.getAddress()).build();
		repo.save(users2);
		return "User Added Succesfully";
	}catch (Exception e) {
		throw e;
	}
	}

	@Override
	public Optional<Users> getUserById(Integer id) {
		try {
		return repo.findById(id);
	}catch (Exception e) {
		throw new ResourceNotFoundException("No user found for id :"+id);
	}
	}

	@Override
	public String deleteUserById(Integer id) {
		try {
			repo.deleteById(id);
		return "User deleted";
	}catch (Exception e) {
		throw new ResourceNotFoundException("No user found");
	}
	}
	
	@Override
	public String updateUsers(Integer id,Users u1) throws NoSuchFieldException {
		try{
			Users u2=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("No User Found for id : "+id));
		u2.setName(u1.getName());
		u2.setMiddelName(u1.getMiddelName());
		u2.setSirName(u1.getSirName());
		u2.setAddress(u1.getAddress());
		u2.setMobile(u1.getMobile());
		repo.save(u2);
		return "User Updated";
	}catch (Exception e) {
		throw e;
	}
}

}