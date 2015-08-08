package com.netty.message;

import java.util.HashMap;
import java.util.Map;

public class Header {
	private int srcCode = 0xabef0101;
	private int length;//消息长度
	private long sessionId;//会话id
	private byte type;    //消息类型
	private byte priority; //消息优先级
	private Map<String,Object> attachment = new HashMap<String,Object>();
	//附件

	public int getSrcCode() {
		return srcCode;
	}

	public void setSrcCode(int srcCode) {
		this.srcCode = srcCode;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getPriority() {
		return priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public Map<String, Object> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, Object> attachment) {
		this.attachment = attachment;
	}
}