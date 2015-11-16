package com.ssh.bean;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.entity.User;
import com.ssh.service.user.UserService;

@RestController("/rest")
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/user/getUserInfo")
	@ResponseBody
	public List<String> getUserInfo() {
		log.info("Request Get User Info");
		return userService.queryAllUserName();
	}

	@RequestMapping("/user/validateLogin")
	@ResponseBody
	public boolean validateLogin(User user) {
		log.info("Request Validate Login Info");
		return userService.validateLogin(user);
	}

	@RequestMapping("/user/registerCheck")
	@ResponseBody
	public boolean registerCheck(User user) {
		log.info("Request Check User Info");
		return userService.checkIsExist(user);
	}

	@RequestMapping("/user/recordUserInfo")
	@ResponseBody
	public boolean RecordUserInfo(User user) {
		log.info("Request Record User Info");
		return userService.recordUserInfo(user);
	}
}
