package com.sglj.fbf.entity.config;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sglj.fbf.base.BaseEntity;
import com.sglj.fbf.entity.config.support.CodeBase;


/**
 * 小类代码
 * @author guanhongwei
 *
 */
@Entity
@Table(name = "LG_CODE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CODE_TYPE", discriminatorType = DiscriminatorType.STRING, length = 30)
@DiscriminatorValue("CODE")
public class CodeEntity extends BaseEntity implements CodeBase {

	private static final long serialVersionUID = 8178632995918187740L;

	/** 编码值 */
	@Column(name = "VALUE", length = 100)
	private String value;

	/** 编码文本 */
	@Column(name = "LABEL", length = 150)
	private String label;

	/** 排序 */
	@Column(name = "SEQ")
	private int seq;

	/** 备注 */
	@Column(name = "REMARK", length = 3000)
	private String remark;

	/** 所属大类 */
	@ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = true)
	private CategoryEntity category;

	/**
	 * 
	 */
	public CodeEntity() {
		super();
	}

	/**
	 * @param value
	 * @param label
	 * @param seq
	 */
	public CodeEntity(String value, String label, Integer seq) {
		super();
		this.value = value;
		this.label = label;
		this.seq = seq;
	}

	/**
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 */
	@Override
	public void setValue(String value) {
		this.value = value;

	}

	/**
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 */
	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setSeq(String seq) {
		if (StringUtils.isNotEmpty(seq)) {
			this.seq = Integer.valueOf(seq);
		}
	}

	/**
	 * @return the category
	 */
	public CategoryEntity getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
