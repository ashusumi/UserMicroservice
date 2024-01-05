package com.UserService.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ratings {
	
	private Integer id;
	private String userId;
	private String hotelId;
	private String feedBack;
	private int ratings;

}
