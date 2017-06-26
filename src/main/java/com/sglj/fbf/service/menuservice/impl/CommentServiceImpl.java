package com.sglj.fbf.service.menuservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.menudao.CommentDao;
import com.sglj.fbf.entity.menu.Comment;
import com.sglj.fbf.entity.menu.Menu;
import com.sglj.fbf.service.menuservice.CommentService;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月8日 下午1:04:19 
 * @version 1.0 
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;
	@Override
	public void addComment(Comment Comment) {
		commentDao.save(Comment);
	}

	@Override
	public void updateComment(Comment Comment) {
		commentDao.update(Comment);
	}

	@Override
	public void removeComment(String id) {
		commentDao.deleteById(id);
	}

	@Override
	public List<Comment> queryCommentList(Menu menu) {
		QueryRule queryRule=QueryRule.getInstance();
		queryRule.addEqual("menu", menu);
		queryRule.addDescOrder("lastModified");
		return commentDao.find(queryRule);
	}

}
