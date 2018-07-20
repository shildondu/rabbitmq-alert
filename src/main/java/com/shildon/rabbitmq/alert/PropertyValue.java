package com.shildon.rabbitmq.alert;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Shildon on 2018/7/17.
 */
public enum PropertyValue {
	RABBITMQ_ADMIN_API_URL("rabbitmq.admin.api.url", "http://172.27.138.1:15672/api", "rabbitmq管理后台api地址"),
	RABBITMQ_ADMIN_USERNAME("rabbitmq.admin.username", "mq", "rabbitmq管理后台账号"),
	RABBITMQ_ADMIN_PASSWORD("rabbitmq.admin.password", "123456", "rabbitmq管理后台密码"),

	QUEUE_READY_MESSAGE_SIZE("queue.ready.message.size", "10000", "队列准备状态消息数量"),
	QUEUE_UNACK_MESSAGE_SIZE("queue.unack.message.size", "10000", "队列未收到应答的消息数量"),
	QUEUE_TOTAL_MESSAGE_SIZE("queue.total.message.size", "10000", "队列总消息数量"),
	QUEUE_CONSUME_SIZE("queue.consume.size", "10000", "队列消费者数量"),

	NODE_MEMORY_USED_MB("node.memory.used.mb", "1024", "结点内存使用容量，单位MBs"),
	NODE_RUNNING("node.running", "true", "结点是否正在运行");

	private String code;
	private String value;
	private String defaultValue;
	private String msg;

	public String getValue() {
		return StringUtils.isEmpty(value) ? defaultValue : value;
	}

	PropertyValue(String code, String defaultValue, String msg) {
		this.code = code;
		this.defaultValue = defaultValue;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "PropertyValue{" +
				"code='" + code + '\'' +
				", value=" + value +
				", defaultValue=" + defaultValue +
				", msg='" + msg + '\'' +
				'}';
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
