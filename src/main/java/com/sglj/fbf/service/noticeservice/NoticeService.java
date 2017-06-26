package com.sglj.fbf.service.noticeservice;

import com.sglj.fbf.crud.query.Page;
import com.sglj.fbf.entity.notice.Notice;

/** 
 * @author  wei 
 * @date 创建时间：2017年3月2日 下午5:45:11 
 * @version 1.0 
 */
public interface NoticeService {
	/**
	 * 保存公告
	 * @param notice
	 */
	public void saveNotice(Notice notice);
	/**
	 * 获取用户的公告
	 * @param userId
	 * @return
	 */
	public Page queryNoticeList(Integer index, Integer size);
	
	/**
	 * 删除公告
	 * @param id
	 */
	public void delNotice(String id);
	
	/**
	 * 更新公告
	 * @param id
	 */
	public void updateNotice(Notice notice);

}
