package com.UserService.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.UserService.Dao.UsersRepo;
import com.UserService.Exceptions.ResourceNotFoundException;
import com.UserService.Model.Hotel;
import com.UserService.Model.Ratings;
import com.UserService.Model.Users;
import com.UserService.Service.UsersService;
import com.UserService.feignClient.service.HotelService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired 
	private UsersRepo repo;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
	public Users getUserById(Integer id) {
		try {
		Users user= repo.findById(id).orElseThrow(()->new ResourceNotFoundException("No User with given user id"));
	Ratings[] arrayList=restTemplate.getForObject("http://RATING-SERVICE/ratings//user/"+id, Ratings[].class);
		List<Ratings> rt=Arrays.stream(arrayList).toList();
	List<Ratings> list=rt.stream().map(ratings->{
			System.out.println(ratings.getHotelId());
//				ResponseEntity<Hotel> entity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/hotelById?id="+ratings.getHotelId(),Hotel.class);
				Hotel hotel=hotelService.getHotels(Integer.valueOf(ratings.getHotelId()));
				ratings.setHotel(hotel);
				return ratings;
		}).collect(Collectors.toList());
		
		user.setRatings(list);
		return user;
	}catch (Exception e) {
		throw e;
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