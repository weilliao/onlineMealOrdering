package com.sglj.fbf.base;

import java.io.Serializable;

import com.sglj.fbf.constant.BusiConstant;

public class ResponseEntity implements Serializable {
	
	private static final long serialVersionUID = 350939574321573616L;
	
	private String status;
	private String error;
	private String msg;
	private Object data;
	private String pageCount;
	private String code;
	
	public ResponseEntity() {
		this.status = BusiConstant.STATUS_SUCCESS;
		this.msg = BusiConstant.MSG.SUCCESS;
	}

	public ResponseEntity(String status) {
		this.status = status;
	}

	public ResponseEntity(String status, String error) {
		this.status = status;
		this.error = error;
	}

	public ResponseEntity(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public ResponseEntity(String status, Object data, String pageCount) {
		this.status = status;
		this.data = data;
		this.pageCount = pageCount;
	}

	public ResponseEntity(String status, String error, String msg, Object data) {
		this.status = status;
		this.error = error;
		this.msg = msg;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public ResponseEntity setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getError() {
		return error;
	}

	public ResponseEntity setError(String error) {
		this.error = error;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseEntity setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResponseEntity setData(Object data) {
		this.data = data;
		return this;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	protected boolean canEqual(Object other) {
		return other instanceof ResponseEntity;
	}
}
