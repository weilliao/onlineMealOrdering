package com.sglj.fbf.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 订单下的菜品类
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午4:29:25 
 * @version 1.0 
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ORDER_MENU")
public class OrderMenu extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品对应所属的订单编号
	 */
    @Column(name = "ORDER_ID")
	private String orderId;
    /**
	 * 菜品名字
	 */  
    @Column(name = "MENU_NAME", length = 60)
	private String menuName;
    
	/**
	 * 菜品ID
	 */  
    @Column(name = "MENU_ID", length = 60)
	private String menuId;
    
    /** 
	 * 菜图片
	 */
	@Column(name = "MENUPIC", length = 150)
	private String menuPic;
    /**
     * 菜品数量
     */
    @Column(name = "MENU_NUM")
    private int menuNum;
    
    /**  价格 */
	@Column(name = "PRICE", length = 150)
	private double price;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuPic() {
		return menuPic;
	}

	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
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
