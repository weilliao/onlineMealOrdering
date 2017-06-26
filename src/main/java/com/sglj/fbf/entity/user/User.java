package com.sglj.fbf.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 前台用戶
 * @author wei
 * @date 创建时间：2016年12月28日 上午11:53:15
 * @version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USER")
public class User extends Position {

	private static final long serialVersionUID = -7195483910729903599L;
	/** 用户名 */
	@Column(name = "USERNAME", length = 150)
	private String username;
	
	/**  用户密码 */
	@Column(name = "PASSWORD", length = 150)
	private String password;
	
	/** 用户头像 */
	@Column(name = "HEADERPIC", length = 150)
	private String headerPic="default.jpg";
	
	/**  用户等级：普通用户、会员  */
	@Column(name = "GRADE", length = 150)
	private String grade="普通用户";
	
	/** 姓名 */
	@Column(name = "NAME", length = 150)
	private String name;

	/** 性别 */
	@Column(name = "GENDER", length = 10)
	private String gender;

	/** 手机号码 */
	@Column(name = "MOBILE", length = 20)
	private String mobile;

	/** 电话号码 */
	@Column(name = "TEL", length = 20)
	private String tel;

	/** 电子邮件 */
	@Column(name = "EMAIL", length = 100)
	protected String email;

	/** 其他信息 */
	@Column(name = "PERSON_OTHER_INFO", length = 300)
	private String personOtherInfo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaderPic() {
		return headerPic;
	}

	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonOtherInfo() {
		return personOtherInfo;
	}

	public void setPersonOtherInfo(String personOtherInfo) {
		this.personOtherInfo = personOtherInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
