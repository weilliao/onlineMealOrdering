package com.sglj.fbf.entity.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 公告类
 * @author  wei 
 * @date 创建时间：2017年3月2日 下午5:34:40 
 * @version 1.0 
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "NOTICE")
public class Notice extends BaseEntity{
	private static final long serialVersionUID = -7195483910729903599L;
	
	/**
	 * 公告标题
	 */
    @Column(name = "NOTICE_TITLE")
	private String noticeTitle;
	
    /** 公告内容 */
	@Column(name = "NOTICE_CONTENT", length = 300)
	private String noticeContent;
}
