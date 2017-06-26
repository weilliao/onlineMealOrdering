package com.sglj.fbf.entity.menu;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;
import com.sglj.fbf.entity.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 
 * 评论类（每个菜品下面的评论）
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午3:04:37 
 * @version 1.0 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity {

	private static final long serialVersionUID = -7195483910729903599L;
	
	
	/**
	 * 评论对应所属的菜品
	 */
	@JoinColumn(name = "MENU_ID")
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private Menu menu;
	
	/**
	 * 评论对应所属的用户
	 */
	@JoinColumn(name = "USER_ID")
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private User user;
	/** 赞或不 */
	@Column(name = "ZAN")
	private Boolean Zan;
	/** 评论的内容 */
	@Column(name = "CONTENT", length = 300)
	private String content;
	
	/** 评论时上传的图片 */
	@Column(name = "PIC", length = 100)
	private String pic;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getZan() {
		return Zan;
	}

	public void setZan(Boolean zan) {
		Zan = zan;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
