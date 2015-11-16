package com.ssh.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssh.dao.BaseDao;
import com.ssh.dao.IBaseDao;
import com.ssh.entity.User;

@Repository("userDao")
public class UserDao extends BaseDao<User, Long> implements
		IBaseDao<User, Long> {

	public UserDao() {
		super(User.class);
		// super.setPersistentClass(User.class);
	}

	public List<User> queryAllUsers() {
		return findAllList();
	}
}
