package com.shildon.rabbitmq.alert;

import com.rabbitmq.http.client.domain.NodeInfo;

/**
 * Created by Shildon on 2018/7/23.
 */
public class NodeInfoWrapper {
	private String targetValue;
	private NodeInfo nodeInfo;

	public NodeInfoWrapper() {

	}

	public NodeInfoWrapper(String targetValue, NodeInfo nodeInfo) {
		this.targetValue = targetValue;
		this.nodeInfo = nodeInfo;
	}

	@Override
	public String toString() {
		return "NodeInfoWrapper{" +
				"targetValue='" + targetValue + '\'' +
				", nodeInfo=" + nodeInfo +
				'}';
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public NodeInfo getNodeInfo() {
		return nodeInfo;
	}

	public void setNodeInfo(NodeInfo nodeInfo) {
		this.nodeInfo = nodeInfo;
	}
}
