package com.UserService.Dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.UserService.Model.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{

	
}
