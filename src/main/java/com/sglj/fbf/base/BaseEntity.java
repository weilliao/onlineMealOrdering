package com.sglj.fbf.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 
 * @author guanhongwei
 *
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7381601484906811004L;

	/** 主键· */
	@Id
	@GeneratedValue(generator = "core_id_generate")
	@GenericGenerator(name = "core_id_generate", strategy = "com.sglj.fbf.crud.dao.generator.CustomUUIDGenerator")
	protected String id;

	/** 首次插入时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_INSERT")
	protected Date firstInsert;

	/** 最后修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED")
	protected Date lastModified;

	/** 创建人 */
	@Column(name = "CREATE_USER")
	protected String createUser;

	/** 修改人 */
	@Column(name = "UPDATE_USER")
	protected String updateUser;

	/** 创建机构 */
	@Column(name = "CREATE_ORG")
	protected String createOrg;

	/** 修改机构 */
	@Column(name = "UPDATE_ORG")
	protected String updateOrg;

	/** 状态 */
	@Column(name = "STATUS", length = 10)
	protected String status;

	/** 数据拥有者 */
	@Column(name = "DATA_OWNER")
	protected String dataOwner;

	/** 数据来源 */
	@Column(name = "DATA_SOURCE")
	protected String dataSource;

	/** 是否已删除 */
	@Column(name = "IS_DELETED")
	protected Boolean isDeleted = false;

	/** 删除原因 */
	@Column(name = "DEL_REASON")
	protected String delReason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFirstInsert() {
		return firstInsert;
	}

	public void setFirstInsert(Date firstInsert) {
		this.firstInsert = firstInsert;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getUpdateOrg() {
		return updateOrg;
	}

	public void setUpdateOrg(String updateOrg) {
		this.updateOrg = updateOrg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataOwner() {
		return dataOwner;
	}

	public void setDataOwner(String dataOwner) {
		this.dataOwner = dataOwner;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDelReason() {
		return delReason;
	}

	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
