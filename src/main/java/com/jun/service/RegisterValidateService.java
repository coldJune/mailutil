package com.jun.service;

import java.util.Date;

import javax.management.ServiceNotFoundException;

import com.jun.dao.UserDao;
import com.jun.mail.MD5Util;
import com.jun.mail.SendMail;
import com.jun.pojo.User;

import freemarker.core.ParseException;

public class RegisterValidateService {
	private UserDao ud = new UserDao();
	
	public void processregister(String email){
		User user  = new User();
		System.out.println(email);
		user.setEmail(email);
		user.setRegisterTime(new Date());
		user.setStatus(0);
		user.setValidateCode(MD5Util.encode2hex(email));
		System.out.println(user);
		ud.save(user);
		
		StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！<br>");
        sb.append("http://localhost:8080/springmvc/user/register?action=activate&email="); 
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("");
        
        SendMail.send(email, sb.toString());
	}
	
	public void processActivate(String email,String validateCode) throws ServiceNotFoundException,ParseException, java.text.ParseException{
		User user =ud.find(email);
		if(user!=null){
			if(user.getStatus()==0){
				Date currentTime = new Date();
				currentTime.before(user.getRegisterTime());
				if(currentTime.before(user.getLastActivateTime())){
					if(validateCode.equals(user.getValidateCode())){
						user.setStatus(1);
						ud.update(user);
					}else{
						throw new ServiceNotFoundException("激活码不正确");
					}
				}else {
					throw new ServiceNotFoundException("激活码已过期");
				}
			}else{
				throw  new ServiceNotFoundException("邮箱已激活，请登录");
			}
		}else{
			throw new ServiceNotFoundException("该邮箱未注册");
		}
	}
	
}
