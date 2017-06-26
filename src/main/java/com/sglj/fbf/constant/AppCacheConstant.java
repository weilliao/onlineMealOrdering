package com.sglj.fbf.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.entity.config.CategoryEntity;
import com.sglj.fbf.entity.config.CodeEntity;
import com.sglj.fbf.exception.ServiceException;
import com.sglj.fbf.service.sysconfigservice.CategoryService;
import com.sglj.fbf.service.sysconfigservice.CodeService;
import com.sglj.fbf.service.sysconfigservice.impl.CodeServiceImpl;
import com.sglj.fbf.spring.SpringBeanService;

/**
 * 系统数据缓存，通常在应用启动时从数据库加载到内存中使用
 * 1.缓存大类代码和小类代码 - 
 * 2.缓存菜单
 * @author guanhongwei
 *
 */
public class AppCacheConstant implements InitializingBean {
	
	private static Logger log = Logger.getLogger(AppCacheConstant.class);
	
	private static final String CodeServiceImplBean = "sysconf.codeService";
	
	@Resource(name = "sysconf.categoryService")
	private CategoryService categoryService;
	
	@Resource(name = CodeServiceImplBean)
	private CodeService codeService;
	
	// 以大类代码为key值，list里放大类下面的小类
	private static Map<String, List<CodeEntity>> categoryCodeMap = new ConcurrentHashMap<String, List<CodeEntity>>();
	
	//大类代码
	private static List<CategoryEntity> categoryList = new Vector<CategoryEntity>();
	
	//小类代码
	private static List<CodeEntity> codeList = new Vector<CodeEntity>();
	
	/**
	 * 缓存小类代码-以大类代码为 key, 小类代码对象为 value
	 */
	private void initCategoryCodeMap() {
		
		log.info("============ cache categoryCodeMap start  =============");
		
		String categoryCode = "";//大类代码
		List<CodeEntity> codeListM;//小类代码
		if (codeList != null && codeList.size() > 0) {
			for (CodeEntity code : codeList) {
				categoryCode = code.getCategory().getCode();
				if(StringUtils.isNotBlank(categoryCode)){
					if (categoryCodeMap.get(categoryCode) != null) {
						codeListM = categoryCodeMap.get(categoryCode);
						codeListM.add(code);
					} else {
						codeListM = new ArrayList<CodeEntity>();
						codeListM.add(code);
						categoryCodeMap.put(code.getCategory().getCode(), codeListM);
					}
				}
			}
		}
		
		log.info("============ cache categoryCodeMap end  =============");
	}
	
	/**
	 * 更据大类代码获取小类代码集合
	 * @param str
	 * @return
	 */
	public static List<CodeEntity> getCodeListByCategoryCode(String str){
		if(StringUtils.isBlank(str)){
			return null;
		}
		
		if(categoryCodeMap.get(str) == null || categoryCodeMap.get(str).isEmpty()){
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addSubQueryRule("category").addEqual("code", str);
			
			CodeServiceImpl bean = SpringBeanService.getBeans(CodeServiceImpl.class, CodeServiceImplBean);
			
			List<CodeEntity> codeList = bean.queryCodeList(queryRule);
			
			if(codeList != null && !codeList.isEmpty()){
				categoryCodeMap.put(str, codeList);
			}
			return codeList;
		}
		
		return categoryCodeMap.get(str);
	}
	
	/**
	 * 更据大类代码、小类代码查询小类对象
	 * @param code 大类代码
	 * @param value 小类代码
	 * @return 找到返回小类代码对象，未找到则返回 null
	 */
	public static CodeEntity getCodeByCategoryCodeAndCode(String code, String value){
		//参数为空,返回null
		if(StringUtils.isBlank(code) || StringUtils.isBlank(value)){
			return null;
		}
		
		List<CodeEntity> codeEntities = AppCacheConstant.getCodeListByCategoryCode(code);
		
		for (CodeEntity temp : codeEntities) {
			if(StringUtils.equals(temp.getValue(), value)){
				//更据小类代码找到小类对象,返回
				return temp;
			}
		}
		//未找到，返回 null
		return null;
	}
	
	/**
	 * 更据大类代码、小类代码查询小类编码文本
	 * @param code 大类代码
	 * @param value 小类代码
	 * @return 找到返回小类代码对象，未找到则返回 null
	 */
	public static String getCodeLabelByCategoryCodeAndCode(String code, String value){
		return AppCacheConstant.getCodeByCategoryCodeAndCode(code, value).getLabel();
	}
	
	/**
	 * 缓存大类代码对象
	 */
	private void initCategoryList(){
		log.info("============ cache categoryList start  =============");
		
		categoryList = categoryService.queryCategory(QueryRule.getInstance());
		
		log.info("============ cache categoryList end  =============");
	}
	
	/**
	 * 缓存小类代码对象
	 */
	private void initCodeList(){
		log.info("============ cache codeList start  =============");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addAscOrder("seq");
		codeList = codeService.queryCodeList(queryRule);
		
		log.info("============ cache codeList end  =============");
	}
	
	/**
	 * 逐个加载缓存
	 */
	private void initCache(){
		try {
			initCategoryList();

			initCodeList();
			
			initCategoryCodeMap();
			
		} catch (ServiceException e) {
			log.error("initCache error : " + e);
		}
	}
	
	/**
	 * 清除缓存
	 */
	private static void clearCache(){
		try {
			categoryCodeMap.clear();
			
			categoryList.clear();
			
			codeList.clear();
			
		} catch (ServiceException e) {
			log.error("clearCache error : " + e);
		}
	}
	
	/**
	 * 重新加载所有缓存
	 */
	public static void refreshAllCache(){
		try {
			//清除缓存
			clearCache();
			
			//加载缓存
			AppCacheConstant n = SpringBeanService.getBeans(AppCacheConstant.class, "AppCacheConstant");
			n.afterPropertiesSet();
			
		} catch (Exception e) {
			log.error("refreshAllCache : " + e);
		}
	}
	
	/**
	 * 应用启动后加载缓存
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initCache();
	}
	
	
 	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
