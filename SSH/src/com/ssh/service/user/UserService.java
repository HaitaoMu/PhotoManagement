package com.ssh.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ssh.dao.user.UserDao;
import com.ssh.entity.User;

@Service("userService")
public class UserService {

	private Logger log = Logger.getLogger(UserService.class);

	@Resource
	private UserDao userDao;

	public List<String> queryAllUserName() {
		List<User> userLists = userDao.queryAllUsers();
		List<String> userNames = new ArrayList<String>();
		String userName = "";
		for (User user : userLists) {
			userName = user.getUserName();
			if (!userNames.contains(userName)) {
				userNames.add(userName);
			}
		}
		return userNames;
	}

	public boolean recordUserInfo(User user) {
		try {
			userDao.save(user);
		} catch (Exception e) {
			log.error("Record User Error :" + e.getMessage());
			return false;
		}
		log.error("Recorded User successfully.");
		return true;
	}

	public boolean validateLogin(User user) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("userName", user.getUserName());
		queryParams.put("passWord", user.getPassWord());
		List<User> userList = userDao.findAllByAttribute(queryParams);
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkIsExist(User user) {
		return validateLogin(user);
	}
}
