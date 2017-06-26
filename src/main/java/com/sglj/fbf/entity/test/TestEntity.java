package com.sglj.fbf.entity.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Entity
@Table(name = "TEST")
@EqualsAndHashCode(callSuper = false)
public class TestEntity extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4981653180870153133L;
	
	@Column(name = "a")
	private String a;
	
	@Transient
	private Date test;

}
