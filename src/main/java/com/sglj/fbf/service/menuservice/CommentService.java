package com.sglj.fbf.service.menuservice;

import java.util.List;

import com.sglj.fbf.entity.menu.Comment;
import com.sglj.fbf.entity.menu.Menu;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月8日 下午12:41:30 
 * @version 1.0 
 */
public interface CommentService {
	
	/**
	 * 增加评论
	 * @param Comment
	 */
	public void addComment(Comment Comment);
	/**
	 * 更新评论信息
	 * @param Comment
	 */
	public void updateComment(Comment Comment);
	
	/**
	 * 删除评论信息
	 * @param Comment
	 */
	public void removeComment(String id);
	/**
	 * 获取餐品下的评论
	 * @param menu
	 * @return
	 */
	public List<Comment> queryCommentList(Menu menu);
	

}
