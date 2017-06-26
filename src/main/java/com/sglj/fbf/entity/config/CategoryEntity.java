/**
 * 
 */
package com.sglj.fbf.entity.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.sglj.fbf.base.BaseEntity;
import com.sglj.fbf.entity.config.support.CategoryBase;

/**
 * 大类代码
 * @author guanhongwei
 *
 */
@Entity
@Table(name = "LG_CATEGORY")
public class CategoryEntity extends BaseEntity implements CategoryBase{

	private static final long serialVersionUID = 935179854301105562L;

	/** 大类代码 */
	@Column(name = "CODE", length = 100)
	private String code;

	/** 大类名称 */
	@Column(name = "NAME", length = 150)
	private String name;

	/** 描述 */
	@Column(name = "DESCRIPTION", length = 300)
	private String description;

	/** 编码集合 */
	@Transient
	private List<CodeEntity> codes = new ArrayList<CodeEntity>();

	/**
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 */
	@Override
	public List<CodeEntity> getCodes() {
		return codes;
	}

	/**
	 */
	@Override
	public CodeEntity getCode(String codeValue) {
		if (codeValue != null && codes != null && !codes.isEmpty()) {
			for (CodeEntity code : codes) {
				if (codeValue.equals(code.getValue())) {
					return code;
				}
			}
		}
		return null;
	}

	/**
	 */
	@Override
	public String getCodeLable(String codeValue) {
		CodeEntity code = getCode(codeValue);
		return code == null ? null : code.getLabel();
	}

	/**
	 */
	@Override
	public List<CodeEntity> getCodes(String label) {
		List<CodeEntity> retVals = new ArrayList<CodeEntity>();

		if (this.codes != null) {
			for (CodeEntity code : this.codes) {
				if (code.getLabel().matches(".*" + label + ".*")) {
					retVals.add(code);
				}
			}
		}

		return retVals;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param codes
	 *            the codes to set
	 */
	public void setCodes(List<CodeEntity> codes) {
		this.codes = codes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
