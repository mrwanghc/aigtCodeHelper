package com.aigt.code.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @description 微信回复文本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "xml")
public class ReplyTextMsg implements Serializable {
	private static final long serialVersionUID = 1306290773068167675L;

	/**
	 * 开发者微信号
	 */
	private String toUserName;

	/**
	 * 发送方账号(openId)
	 */
	private String fromUserName;

	/**
	 * 消息创建时间(整型)
	 */
	private Integer createTime;

	/**
	 * 消息类型
	 * 1：text 文本消息
	 * 2：image 图片消息
	 * 3：voice 语音消息
	 * 4：video 视频消息
	 * 5：location 地址位置消息
	 * 6：link 链接消息
	 * 7：event 事件
	 */
	private String msgType;
	/**
	 * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 */
	private String content;

	public ReplyTextMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
	}
}