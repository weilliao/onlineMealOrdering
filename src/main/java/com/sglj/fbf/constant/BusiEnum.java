package com.sglj.fbf.constant;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * 状态/类型枚举
 * @author guanhongwei
 *
 */
public class BusiEnum {
	
	/**
	 * 场景审批状态
	 * 编排中、审核中、已发布
	 * @author guanhongwei
	 *
	 */
	public static enum INSTANCE_AUDIT{
		EDIT("0", "编排中"), AUDIT("1", "审核中"), OK("2", "已发布"), FAILED("3", "已驳回");
		
		INSTANCE_AUDIT(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		/**
		 * 代码
		 */
		private String code;
		
		/**
		 * 描述
		 */
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		/**
		 * 更据code获取desc
		 * @param code
		 * @return
		 */
		public static String getDescByCode(String code) {
			for (INSTANCE_AUDIT e : INSTANCE_AUDIT.values()) {
				if (e.getCode().equals(code)) {
					return e.desc;
				}
			}
			return StringUtils.EMPTY;
		}
		
		/**
		 * 将枚举的kv放入 map
		 * @return
		 */
		public static Map<String, String> enmuKV() {
			Map<String, String> map = Maps.newHashMap();
			for (INSTANCE_AUDIT e : INSTANCE_AUDIT.values()) {
				map.put(e.getCode(), e.getDesc());
			}
			return map;
		}
	}
	
	/**
	 * 场景类型
	 * 合规、发布、巡检、其他
	 * @author guanhongwei
	 *
	 */
	public static enum INSTANCE_TYPE{
		HG("0", "合规"), FB("1", "发布"), XJ("2", "巡检"), OTHER("99999", "其他");
		
		INSTANCE_TYPE(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		/**
		 * 代码
		 */
		private String code;
		
		/**
		 * 描述
		 */
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		/**
		 * 更据code获取desc
		 * @param code
		 * @return
		 */
		public static String getDescByCode(String code) {
			for (INSTANCE_TYPE e : INSTANCE_TYPE.values()) {
				if (e.getCode().equals(code)) {
					return e.desc;
				}
			}
			return StringUtils.EMPTY;
		}
		
		/**
		 * 将枚举的kv放入 map
		 * @return
		 */
		public static Map<String, String> enmuKV() {
			Map<String, String> map = Maps.newHashMap();
			for (INSTANCE_TYPE e : INSTANCE_TYPE.values()) {
				map.put(e.getCode(), e.getDesc());
			}
			return map;
		}
	}
	
	/**
	 * 场景参数类型
	 * 文本型、数值型、布尔型、json
	 * @author guanhongwei
	 *
	 */
	public static enum INSTANCE_PARAMS{
		TEXT("0", "文本型"), NUMBER("1", "数值型"), BOOLEAN("2", "布尔型"), JSON("3", "json");
		
		INSTANCE_PARAMS(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		/**
		 * 代码
		 */
		private String code;
		
		/**
		 * 描述
		 */
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		/**
		 * 更据code获取desc
		 * @param code
		 * @return
		 */
		public static String getDescByCode(String code) {
			for (INSTANCE_PARAMS e : INSTANCE_PARAMS.values()) {
				if (e.getCode().equals(code)) {
					return e.desc;
				}
			}
			return StringUtils.EMPTY;
		}
		
		/**
		 * 将枚举的kv放入 map
		 * @return
		 */
		public static Map<String, String> enmuKV() {
			Map<String, String> map = Maps.newHashMap();
			for (INSTANCE_PARAMS e : INSTANCE_PARAMS.values()) {
				map.put(e.getCode(), e.getDesc());
			}
			return map;
		}
	}
	
	/**
	 * 作业类型
	 * 主机、应用、网络
	 * @author guanhongwei
	 *
	 */
	public static enum TASK_TYPE{
		HOST("0", "主机"), APP("1", "应用"), NET("2", "网络"), DB("3", "数据库");
		
		TASK_TYPE(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		/**
		 * 代码
		 */
		private String code;
		
		/**
		 * 描述
		 */
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		/**
		 * 更据code获取desc
		 * @param code
		 * @return
		 */
		public static String getDescByCode(String code) {
			for (TASK_TYPE e : TASK_TYPE.values()) {
				if (e.getCode().equals(code)) {
					return e.desc;
				}
			}
			return StringUtils.EMPTY;
		}
		
		/**
		 * 将枚举的kv放入 map
		 * @return
		 */
		public static Map<String, String> enmuKV() {
			Map<String, String> map = Maps.newHashMap();
			for (TASK_TYPE e : TASK_TYPE.values()) {
				map.put(e.getCode(), e.getDesc());
			}
			return map;
		}
	}
	
	/**
	 * 作业注册状态
	 * 已注册(1)，未注册(0)
	 * @author guanhongwei
	 *
	 */
	public static enum TASK_HAD_REGISTED{
		NO("0", "未注册"), YES("1", "已注册");
		
		TASK_HAD_REGISTED(String code, String desc){
			this.code = code;
			this.desc = desc;
		}
		
		/**
		 * 代码
		 */
		private String code;
		
		/**
		 * 描述
		 */
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		/**
		 * 更据code获取desc
		 * @param code
		 * @return
		 */
		public static String getDescByCode(String code) {
			for (TASK_HAD_REGISTED e : TASK_HAD_REGISTED.values()) {
				if (e.getCode().equals(code)) {
					return e.desc;
				}
			}
			return StringUtils.EMPTY;
		}
		
		/**
		 * 将枚举的kv放入 map
		 * @return
		 */
		public static Map<String, String> enmuKV() {
			Map<String, String> map = Maps.newHashMap();
			for (TASK_HAD_REGISTED e : TASK_HAD_REGISTED.values()) {
				map.put(e.getCode(), e.getDesc());
			}
			return map;
		}
	}
	
	public static void main(String[] args) {
		System.err.println(BusiEnum.INSTANCE_TYPE.FB.desc);
		System.err.println(Arrays.toString(BusiEnum.INSTANCE_TYPE.values()));
		for (BusiEnum.INSTANCE_TYPE temp : BusiEnum.INSTANCE_TYPE.values()) {
			System.err.println(temp.code + " : " + temp.desc);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
