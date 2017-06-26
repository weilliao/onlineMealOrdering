package com.sglj.fbf.controller.notice;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.notice.Notice;
import com.sglj.fbf.service.noticeservice.NoticeService;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月2日 下午5:56:04 
 * @version 1.0 
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Resource
	private NoticeService noticeService;
	/**
	 * 保存公告
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/saveNotice", produces = "application/json")
	private ResponseEntity saveNotice(Notice notice) {
		System.out.println(notice.toString());
	    
		ResponseEntity rst = new ResponseEntity();
		try {
			noticeService.saveNotice(notice);
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
	 * 删除公告
	 * @param notice
	 * @return
	 */
	@RequestMapping(value = "/delNotice", produces = "application/json")
	private ResponseEntity delNotice(String id) {
		System.out.println(id);
		ResponseEntity rst = new ResponseEntity();
		try {
			noticeService.delNotice(id);
			rst.setMsg("成功");
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg("失败");
			return rst;
		}
		return rst;
	}
	
	
	/**
	 * 后台获取公告列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getNoticeList(String index,String pagesize) {
		ResponseEntity rst = new ResponseEntity();
		try {
			System.out.println("INDEX:"+index+"PAGESIZE:"+pagesize);
			
			Page page = noticeService.queryNoticeList(Integer.parseInt(index),Integer.parseInt(pagesize));
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
	 * 获取最新公告列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNotice", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getNotice() {
		ResponseEntity rst = new ResponseEntity();
		try {
			//拿出前5条
			Page page = noticeService.queryNoticeList(1, 5);
			rst.setData(page);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	}
}
