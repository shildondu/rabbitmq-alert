package com.shildon.rabbitmq.alert;

import com.rabbitmq.http.client.domain.NodeInfo;
import com.rabbitmq.http.client.domain.QueueInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 各个阈值判断持有类
 * Created by Shildon on 2018/7/17.
 */
public class JudgementHolder {

	// 结点阈值判断列表
	public static final List<BiConsumer<NodeInfo, JudgeResult>> NODE_JUDGEMENTS = new LinkedList<>();
	// 队列阈值判断列表
	public static final List<BiConsumer<QueueInfo, JudgeResult>> QUEUE_JUDGEMENTS = new LinkedList<>();

	static {
		NODE_JUDGEMENTS.add(((nodeInfo, judgeResult) -> {
			long limitValue = Long.valueOf(PropertyValue.NODE_MEMORY_USED_MB.getValue()) * 1024 * 1024;
			long targetValue = nodeInfo.getMemoryUsed();
			boolean over = targetValue > limitValue;
			if (over) {
				judgeResult.getOverNodes(PropertyValue.NODE_MEMORY_USED_MB)
						.add(new NodeInfoWrapper(String.valueOf(targetValue), nodeInfo));
			}
		}));

		NODE_JUDGEMENTS.add(((nodeInfo, judgeResult) -> {
			boolean runningCheck = Boolean.valueOf(PropertyValue.NODE_RUNNING.getValue());
			if (runningCheck) {
				if (!nodeInfo.isRunning()) {
					judgeResult.getOverNodes(PropertyValue.NODE_RUNNING)
							.add(new NodeInfoWrapper(String.valueOf(nodeInfo.isRunning()), nodeInfo));
				}
			}
		}));

		QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
			long limitValue = Long.valueOf(PropertyValue.QUEUE_READY_MESSAGE_SIZE.getValue());
			long targetValue = queueInfo.getMessagesReady();
			boolean over = targetValue > limitValue;
			if (over) {
				judgeResult.getOverQueues(PropertyValue.QUEUE_READY_MESSAGE_SIZE)
						.add(new QueueInfoWrapper(String.valueOf(targetValue), queueInfo));
			}
		});

		QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
			long limitValue = Long.valueOf(PropertyValue.QUEUE_UNACK_MESSAGE_SIZE.getValue());
			long targetValue = queueInfo.getMessagesUnacknowledged();
			boolean over = targetValue > limitValue;
			if (over) {
				judgeResult.getOverQueues(PropertyValue.QUEUE_UNACK_MESSAGE_SIZE)
						.add(new QueueInfoWrapper(String.valueOf(targetValue), queueInfo));
			}
		});

		QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
			long limitValue = Long.valueOf(PropertyValue.QUEUE_TOTAL_MESSAGE_SIZE.getValue());
			long targetValue = queueInfo.getTotalMessages();
			boolean over = targetValue > limitValue;
			if (over) {
				judgeResult.getOverQueues(PropertyValue.QUEUE_TOTAL_MESSAGE_SIZE)
						.add(new QueueInfoWrapper(String.valueOf(targetValue), queueInfo));
			}
		});

		QUEUE_JUDGEMENTS.add(((queueInfo, judgeResult) -> {
			long limitValue = Long.valueOf(PropertyValue.QUEUE_CONSUME_SIZE.getValue());
			long targetValue = queueInfo.getConsumerCount();
			boolean over = targetValue > limitValue;
			if (over) {
				judgeResult.getOverQueues(PropertyValue.QUEUE_CONSUME_SIZE)
						.add(new QueueInfoWrapper(String.valueOf(targetValue), queueInfo));
			}
		}));
	}

}
