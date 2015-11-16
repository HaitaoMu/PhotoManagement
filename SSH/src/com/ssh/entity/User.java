package com.ssh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USER")
public @Data class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3807034355903733554L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@SequenceGenerator(name = "USER_ID_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "USER_ID_SEQ")
	private Long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String passWord;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "QQ_NUMBER")
	private String qqNumber;

	@Column(name = "EMAIL_ADRESS")
	private String emailAdress;

	@Column(name = "NICK_NAME")
	private String nickName;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "LOGIN_ADRESS")
	private String loginAdress;

	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLoginTime;
}
