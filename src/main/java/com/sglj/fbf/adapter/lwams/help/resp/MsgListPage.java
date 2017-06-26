package com.sglj.fbf.adapter.lwams.help.resp;
/**
 * 消息数据读取帮助类
 * @author liuming
 *
 */
public class MsgListPage {

	private Integer total;
	
	private Rows rows;
	
	private boolean success;
	
	public class  Rows{
		private String id;
		private String type;
		private String time;
		private String  sender;
		private String sender_uuid;
		private String sender_url;
		private String text;
		private String state;
		private boolean isRead;
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
		/**
		 * @return the time
		 */
		public String getTime() {
			return time;
		}
		/**
		 * @param time the time to set
		 */
		public void setTime(String time) {
			this.time = time;
		}
		/**
		 * @return the sender
		 */
		public String getSender() {
			return sender;
		}
		/**
		 * @param sender the sender to set
		 */
		public void setSender(String sender) {
			this.sender = sender;
		}
		/**
		 * @return the sender_uuid
		 */
		public String getSender_uuid() {
			return sender_uuid;
		}
		/**
		 * @param sender_uuid the sender_uuid to set
		 */
		public void setSender_uuid(String sender_uuid) {
			this.sender_uuid = sender_uuid;
		}
		/**
		 * @return the sender_url
		 */
		public String getSender_url() {
			return sender_url;
		}
		/**
		 * @param sender_url the sender_url to set
		 */
		public void setSender_url(String sender_url) {
			this.sender_url = sender_url;
		}
		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}
		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}
		/**
		 * @return the isRead
		 */
		public boolean isRead() {
			return isRead;
		}
		/**
		 * @param isRead the isRead to set
		 */
		public void setRead(boolean isRead) {
			this.isRead = isRead;
		}
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public Rows getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Rows rows) {
		this.rows = rows;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
