package com.ssh.bean;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.entity.User;
import com.ssh.service.user.UserService;

@RestController("/rest")
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/user/getUserInfo")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getUserInfo() {
		log.info("Request Get User Info");
		return userService.queryAllUserName();
	}

	@RequestMapping("/user/validateLogin")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateLogin(User user) {
		log.info("Request Validate Login Info");
		return userService.validateLogin(user);
	}

	@RequestMapping("/user/registerCheck")
	@ResponseStatus(HttpStatus.OK)
	public boolean registerCheck(User user) {
		log.info("Request Check User Info");
		return userService.checkIsExist(user);
	}

	@RequestMapping("/user/recordUserInfo")
	@ResponseStatus(HttpStatus.OK)
	public boolean RecordUserInfo(User user) {
		log.info("Request Record User Info");
		return userService.recordUserInfo(user);
	}
}
