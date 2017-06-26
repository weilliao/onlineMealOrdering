package com.sglj.fbf.entity.diningcar;

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
 * 餐车下的菜品类
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午4:29:25 
 * @version 1.0 
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "DININGCAR_MENU")
public class DiningCarMenu extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品对应所属的用户
	 */
	@JoinColumn(name = "USER_ID")
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private User user;
	/** 
	 * 菜图片
	 */
	@Column(name = "MENUPIC", length = 150)
	private String menuPic;
	/**
	 * 菜品
	 */  
    @Column(name = "MENU_ID", length = 60)
	private String menuId;
    /**
     * 菜品数量
     */
    @Column(name = "MENU_NUM")
    private int menuNum;
    
    /** 菜名 */
	@Column(name = "NAME", length = 150)
	private String name;
	
	/**  价格 */
	@Column(name = "PRICE", length = 150)
	private double price;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMenuPic() {
		return menuPic;
	}

	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
