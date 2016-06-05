package com.jun.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.jun.pojo.User;

public class UserDao {
	public HashMap<String, String> map =  new HashMap<String,String>();
	public void save(User user){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time  = sdf.format(user.getRegisterTime());
		map.put("registerTime", time);
		map.put("email", user.getEmail());
		map.put("validateCode", user.getValidateCode());
		map.put("status", String.valueOf(user.getStatus()));
	}
	
	public User find(String email) throws ParseException{
		User user = new User();
		user.setEmail(map.get("email"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date day = sdf.parse(map.get("registerTime"));
		user.setStatus(Integer.valueOf(map.get("status")));
		user.setValidateCode(map.get("validateCode"));
		return user;
	}
	
	public void update(User user){
		map.put("email", user.getEmail());
		map.put("validateCode", user.getValidateCode());
		Date time = user.getRegisterTime();
		int status =user.getStatus();
		map.put("status", String.valueOf(status));
	}
}
