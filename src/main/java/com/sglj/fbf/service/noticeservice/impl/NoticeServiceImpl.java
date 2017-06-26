package com.sglj.fbf.service.noticeservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.crud.query.QueryRule;
import com.sglj.fbf.dao.noticedao.NoticeDao;
import com.sglj.fbf.entity.notice.Notice;
import com.sglj.fbf.service.noticeservice.NoticeService;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月2日 下午5:46:12 
 * @version 1.0 
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeDao noticeDao;

	@Override
	public Page queryNoticeList(Integer index, Integer size) {
		QueryRule queryRule = new QueryRule();
		queryRule.addDescOrder("lastModified");
		return noticeDao.find(queryRule,index,size);
	}

	@Override
	public void delNotice(String id) {
		noticeDao.deleteById(id);
	}

	@Override
	public void updateNotice(Notice notice) {
		noticeDao.update(notice);
	}

	@Override
	public void saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.save(notice);
	}
}
