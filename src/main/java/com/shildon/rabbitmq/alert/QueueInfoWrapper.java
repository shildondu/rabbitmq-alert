package com.shildon.rabbitmq.alert;

import com.rabbitmq.http.client.domain.QueueInfo;

/**
 * Created by Shildon on 2018/7/23.
 */
public class QueueInfoWrapper {
	private String targetValue;
	private QueueInfo queueInfo;

	public QueueInfoWrapper() {

	}

	public QueueInfoWrapper(String targetValue, QueueInfo queueInfo) {
		this.targetValue = targetValue;
		this.queueInfo = queueInfo;
	}

	@Override
	public String toString() {
		return "QueueInfoWrapper{" +
				"targetValue='" + targetValue + '\'' +
				", queueInfo=" + queueInfo +
				'}';
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public QueueInfo getQueueInfo() {
		return queueInfo;
	}

	public void setQueueInfo(QueueInfo queueInfo) {
		this.queueInfo = queueInfo;
	}
}
