package com.sglj.fbf.controller.menu;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sglj.fbf.base.ResponseEntity;
import com.sglj.fbf.constant.BusiConstant;
import com.sglj.fbf.entity.menu.Comment;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.service.menuservice.CommentService;
import com.sglj.fbf.service.menuservice.MenuService;
import com.sglj.fbf.service.userservice.UserService;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月8日 下午1:09:46 
 * @version 1.0 
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Resource
	private CommentService commentService;
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value = "/saveComment", produces = "application/json")
	private ResponseEntity saveComment(Comment comment,String zan, String userId,String menuId) {
		ResponseEntity rst = new ResponseEntity();
		try {
			comment.setUser(userService.getUser(userId));
			comment.setMenu(menuService.getMenuById(menuId));
			if (zan.equals("yes")||zan=="yes") {
				comment.setZan(true);
			}else{
				comment.setZan(false);
			}
			System.out.println(comment.toString());
			commentService.addComment(comment);
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
	@RequestMapping(value = "/getCommentList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity getCommentList(String id) {
		ResponseEntity rst = new ResponseEntity();
		try {
			Menu menu = menuService.getMenuById(id);
			rst.setData(commentService.queryCommentList(menu));
			rst.setStatus(BusiConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			rst.setStatus(BusiConstant.STATUS_ERROR);
			rst.setMsg(e.getMessage());
			return rst;
		}
		return rst;
	} 
	
}
