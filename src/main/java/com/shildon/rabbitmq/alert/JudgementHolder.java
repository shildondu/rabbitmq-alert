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
            boolean over = nodeInfo.getMemoryUsed() > limitValue;
            if (over) {
                judgeResult.getOverNodes(PropertyValue.NODE_MEMORY_USED_MB).add(nodeInfo);
            }
        }));

        NODE_JUDGEMENTS.add(((nodeInfo, judgeResult) -> {
            boolean runningCheck = Boolean.valueOf(PropertyValue.NODE_RUNNING.getValue());
            if (runningCheck) {
                if (!nodeInfo.isRunning()) {
                    judgeResult.getOverNodes(PropertyValue.NODE_RUNNING).add(nodeInfo);
                }
            }
        }));

        QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
            long limitValue = Long.valueOf(PropertyValue.QUEUE_READY_MESSAGE_SIZE.getValue());
            boolean over = queueInfo.getMessagesReady() > limitValue;
            if (over) {
                judgeResult.getOverQueues(PropertyValue.QUEUE_READY_MESSAGE_SIZE).add(queueInfo);
            }
        });

        QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
            long limitValue = Long.valueOf(PropertyValue.QUEUE_UNACK_MESSAGE_SIZE.getValue());
            boolean over = queueInfo.getMessagesUnacknowledged() > limitValue;
            if (over) {
                judgeResult.getOverQueues(PropertyValue.QUEUE_UNACK_MESSAGE_SIZE).add(queueInfo);
            }
        });

        QUEUE_JUDGEMENTS.add((queueInfo, judgeResult) -> {
            long limitValue = Long.valueOf(PropertyValue.QUEUE_TOTAL_MESSAGE_SIZE.getValue());
            boolean over = queueInfo.getTotalMessages() > limitValue;
            if (over) {
                judgeResult.getOverQueues(PropertyValue.QUEUE_TOTAL_MESSAGE_SIZE).add(queueInfo);
            }
        });

        QUEUE_JUDGEMENTS.add(((queueInfo, judgeResult) -> {
            long limitValue = Long.valueOf(PropertyValue.QUEUE_CONSUME_SIZE.getValue());
            boolean over = queueInfo.getConsumerCount() > limitValue;
            if (over) {
                judgeResult.getOverQueues(PropertyValue.QUEUE_CONSUME_SIZE).add(queueInfo);
            }
        }));
    }

}
