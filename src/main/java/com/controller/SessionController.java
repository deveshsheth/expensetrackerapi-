package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.SessionDao;

@RestController
public class SessionController {
	
	@Autowired
	SessionDao signupDao;

	@PostMapping("/signup")
	public ResponseBean<UserBean> insertUser(@RequestBody UserBean userBean) {
		ResponseBean<UserBean> response = new ResponseBean<>();
		if (signupDao.getUserByEmail(userBean.getEmail()) != null) {
			response.setMessage("Email Already Register");
			response.setStatus(201);
		} else {
			signupDao.insertUser(userBean);
			response.setData(userBean);
			response.setMessage("user signup successfully....!!");
			response.setStatus(200);
		}

		return response;
	}
	
	@PostMapping("/login")
	public ResponseBean<UserBean> Login(@RequestBody LoginBean loginBean) {
		UserBean signup = null;
		System.out.println(loginBean.getEmail());
		System.out.println(loginBean.getPassword());
		ResponseBean<UserBean> response = new ResponseBean<>();
		signup = signupDao.login(loginBean.getEmail(), loginBean.getPassword());
		if (signup == null) {
			response.setMessage("Invalid Credentails..!!");
			response.setStatus(201);
		} else {
			response.setData(signup);
			response.setMessage("user login....!!");
			response.setStatus(200);
		}

		return response;
	}
}
