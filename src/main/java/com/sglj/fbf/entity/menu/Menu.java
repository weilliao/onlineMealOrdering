package com.sglj.fbf.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sglj.fbf.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * 菜品类
 * @author  wei 
 * @date 创建时间：2016年12月28日 下午2:45:45 
 * @version 1.0 
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "MENU")
public class Menu extends BaseEntity {

	private static final long serialVersionUID = -7195483910729903599L;
	/** 菜图片*/
	@Column(name = "MENUPIC", length = 150)
	private String menuPic;
	/** 菜名 */
	@Column(name = "NAME", length = 150)
	private String name;
	
	/**  价格 */
	@Column(name = "PRICE", length = 150)
	private double price;
	
	/**  月成交额 */
	@Column(name = "MONTHVOLUME", length = 15)
	private int monthVolume=0;
	
	/** 所属种类：早餐、中餐、晚餐、宵夜 */
	@Column(name = "CATEGORY", length = 150)
	private String category;
	
	/**  评分  */
	@Column(name = "SCORE", length = 150)
	private String score;
	
	/**  库存  */
	@Column(name = "STOCK")
	private int stock;

	/** 备注 */
	@Column(name = "REMARK", length = 60)
	private String remark;

	public String getMenuPic() {
		return menuPic;
	}

	public void setMenuPic(String menuPic) {
		this.menuPic = menuPic;
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

	public int getMonthVolume() {
		return monthVolume;
	}

	public void setMonthVolume(int monthVolume) {
		this.monthVolume = monthVolume;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
