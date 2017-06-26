package com.sglj.fbf.entity.sysuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 后台用户（管理员）
 * <p>Title:  </p>
 * <p>Description:  </p>
 * @author wei
 * @date
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "Backstage_USER")
@Entity(name = "com.sglj.fbf.entity.sysuser.SysUser")
public class SysUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 账户名 */
	@Column(name = "USERNAME", length = 100)
	private String username;

	/** 密码 */
	@Column(name = "PASSWORD", length = 100)
	private String password;
	
	


	/** 备注 */
	@Column(name = "REMARK", length = 300)
	private String remark;




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




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
