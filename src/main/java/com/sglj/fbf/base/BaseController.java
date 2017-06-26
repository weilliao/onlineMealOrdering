package com.sglj.fbf.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public final static Integer currentPage = 1;
	public final static Integer pageSize = 10;
	

}
