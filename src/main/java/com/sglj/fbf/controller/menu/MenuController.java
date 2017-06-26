package com.sglj.fbf.controller.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sglj.fbf.annotation.Authorization;
import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.AppCacheConstant;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.service.menuservice.MenuService;
import com.sglj.fbf.utils.DelFile;
import com.sglj.fbf.utils.FileUtil;

/** 
 * @author  wei 
 * @date 创建时间：2017年1月11日 下午3:13:13 
 * @version 1.0 
 */

@RestController
@RequestMapping("/Menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	/**
	 * 前台根据类别查询菜单列表
	 * @param category
	 * @param index
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getQtMeunList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getUserList(String category,int index,int pagesize,HttpServletRequest request) {
		ResponseEntity rst = new ResponseEntity();
		try {
			if (StringUtils.isBlank(category)) {
				category=null;
			}
			System.out.println("MSG:"+category+"INDEX:"+index+"PAGESIZE:"+pagesize);
			Page page = menuService.getQtMenuList(category,index,pagesize);
			System.out.println("总条数"+page.getTotalCount()+"总页数"+page.getTotalPage());
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
			
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	/**
	 * 前台模糊查询菜单列表
	 * @param msg
	 * @param index
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/queryQtMenuList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity queryQtMenuList(String msg,int index,int pagesize,HttpServletRequest request) {
		ResponseEntity rst = new ResponseEntity();
		try {
			System.out.println("----------------------------------");
			String ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    }
		    System.out.println("IP------"+ip);
		    
			if (StringUtils.isBlank(msg)) {
				msg=null;
			}
			System.out.println("MSG:"+msg+"INDEX:"+index+"PAGESIZE:"+pagesize);
			Page page = menuService.queryQtMenuList(msg,index,pagesize);
			System.out.println("总条数"+page.getTotalCount()+"总页数"+page.getTotalPage());
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	
	@RequestMapping(value = "/getMeun", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getMeun(String id) {
		ResponseEntity rst = new ResponseEntity();
		try {
			rst.setData(menuService.getMenuById(id));
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
	
	

	/**
	 * 后台添加新菜品
	 * @param menu
	 * @return
	 */
	
	@RequestMapping(value = "/saveMeun", produces = "application/json")
	private ResponseEntity saveMeun(Menu menu,@RequestParam(value = "menuPic1", required = false) CommonsMultipartFile menuPic1,HttpServletRequest request, HttpServletResponse response) {
		System.out.println(menuPic1.getOriginalFilename()+"  "+menu.getPrice());
		System.out.println(menu.toString());
		
		ResponseEntity rst = new ResponseEntity();
		try {
			// 后台菜品图片存放的的地址
			String MENU_IMG_SYSTEM = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(BusiConstant.MENU_URL,
					BusiConstant.MENU_IMG_SYSTEM);
			// 后台菜品图片存放的的地址
			String MENU_IMG_QIANTAI = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(BusiConstant.MENU_URL,
					BusiConstant.MENU_IMG_QIANTAI);
			String imgName=FileUtil.uploadFile(menuPic1,"",MENU_IMG_SYSTEM, request);
			FileUtil.uploadFile(menuPic1,imgName,MENU_IMG_QIANTAI, request);
			menu.setMenuPic(imgName);
			menuService.addMenu(menu);
			rst.setMsg("添加成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("添加失败");
			return rst;
		}
		return rst;
	}
	
	
	/**
	 * 后台根据类别查询菜单列表
	 * @param category
	 * @param index
	 * @param pagesize
	 * @return
	 */
	@Authorization
	@RequestMapping(value = "/getMeunListSystem", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getUserListSystem(String category,int index,int pagesize) {
		ResponseEntity rst = new ResponseEntity();
		try {
			if (StringUtils.isBlank(category)) {
				category=null;
			}
			System.out.println("MSG:"+category+"INDEX:"+index+"PAGESIZE:"+pagesize);
			Page page = menuService.getMenuList(category,index,pagesize);
			System.out.println("总条数"+page.getTotalCount()+"总页数"+page.getTotalPage());
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	/**
	 * 后台模糊查询菜单列表
	 * @param msg
	 * @param index
	 * @param pagesize
	 * @return
	 */
	@Authorization
	@RequestMapping(value = "/queryMenuListSystem", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity queryMenuListSystem(String msg,int index,int pagesize) {
		ResponseEntity rst = new ResponseEntity();
		try {
			if (StringUtils.isBlank(msg)) {
				msg=null;
			}
			System.out.println("MSG:"+msg+"INDEX:"+index+"PAGESIZE:"+pagesize);
			Page page = menuService.queryMenuList(msg,index,pagesize);
			System.out.println("总条数"+page.getTotalCount()+"总页数"+page.getTotalPage());
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	/**
	 * 删除餐品信息
	 */
	@RequestMapping(value = "/delMenu", produces = "application/json")
	private ResponseEntity delMenu(String id,String imgName,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			System.out.println("DELETE:"+id+"imgName:"+imgName);
			menuService.removeMunu(id);
			// 后台菜品图片存放的的地址
			String MENU_IMG_SYSTEM = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(BusiConstant.MENU_URL,
					BusiConstant.MENU_IMG_SYSTEM);
			// 后台菜品图片存放的的地址
			String MENU_IMG_QIANTAI = AppCacheConstant.getCodeLabelByCategoryCodeAndCode(BusiConstant.MENU_URL,
					BusiConstant.MENU_IMG_QIANTAI);
			
			DelFile.delFile(MENU_IMG_SYSTEM+imgName);
			DelFile.delFile(MENU_IMG_QIANTAI+imgName);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	
	/**
	 * 餐品上架与下架
	 */
	@RequestMapping(value = "/isSXMenu", produces = "application/json")
	private ResponseEntity isSXMenu(String id,boolean isxiajia,HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity rst = new ResponseEntity();
		try {
			System.out.println("ID:"+id+"isxiajia:"+isxiajia);
			menuService.removeMunu(id,isxiajia);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
	
	
	/**
	 * 后台更新菜品信息
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "/updateMeun", produces = "application/json")
	private ResponseEntity updateMeun(Menu menu,HttpServletRequest request, HttpServletResponse response) {
		System.out.println(menu.toString());
		ResponseEntity rst = new ResponseEntity();
		try {
			Menu menu1=menuService.getMenuById(menu.getId());
			menu1.setCategory(menu.getCategory());
			menu1.setName(menu.getName());
			menu1.setPrice(menu.getPrice());
			menu1.setRemark(menu.getRemark());
			menu1.setStock(menu.getStock());
			menu1.setIsDeleted(menu.getIsDeleted());
			menuService.updateMunu(menu1);
			rst.setMsg("成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("失败");
			return rst;
		}
		return rst;
	}
}
