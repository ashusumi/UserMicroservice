package com.UserService.Service;

import java.util.List;
import java.util.Optional;
import com.UserService.Model.Users;

public interface UsersService {
	
	public List<Users> getAllUsers();
	
	public String addUsers(Users users);
	
	public Users getUserById(Integer id);
	
	public String deleteUserById(Integer id);
	
	public String updateUsers(Integer id,Users users) throws NoSuchFieldException;
	
	
	
	
	

}
