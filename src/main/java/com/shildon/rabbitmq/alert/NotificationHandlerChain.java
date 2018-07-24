package com.shildon.rabbitmq.alert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 超过阈值处理调用链，不保证前后调用顺序
 * Created by Shildon on 2018/7/17.
 */
public class NotificationHandlerChain {

    private List<NotificationHandler> notificationHandlers = new LinkedList<>();

    public void process(JudgeResult judgeResult) {
        if (notificationHandlers.size() == 0) {
            return;
        }

        filterEmptyOverNodes(judgeResult.getOverNodeMap());
        filterEmptyOverQueues(judgeResult.getOverQueueMap());

        notificationHandlers.parallelStream()
                .forEach(notificationHandler -> notificationHandler.onHandle(judgeResult));
    }

    public NotificationHandlerChain addOverHandlers(List<NotificationHandler> notificationHandlers) {
        this.notificationHandlers.addAll(notificationHandlers);
        return this;
    }

    public NotificationHandlerChain addOverHandler(NotificationHandler notificationHandler) {
        this.notificationHandlers.add(notificationHandler);
        return this;
    }

    public void setNotificationHandlers(List<NotificationHandler> notificationHandlers) {
        this.notificationHandlers = notificationHandlers;
    }

    private Map<PropertyValue, List<NodeInfoWrapper>> filterEmptyOverNodes(Map<PropertyValue, List<NodeInfoWrapper>> overNodeMap) {
        Set<Map.Entry<PropertyValue, List<NodeInfoWrapper>>> entrySet = overNodeMap.entrySet();
        entrySet.removeIf(entry -> entry.getValue() == null || entry.getValue().size() == 0);
        return overNodeMap;
    }

    private Map<PropertyValue, List<QueueInfoWrapper>> filterEmptyOverQueues(Map<PropertyValue, List<QueueInfoWrapper>> overQueueMap) {
        Set<Map.Entry<PropertyValue, List<QueueInfoWrapper>>> entrySet = overQueueMap.entrySet();
        entrySet.removeIf(entry -> entry.getValue() == null || entry.getValue().size() == 0);
        return overQueueMap;
    }
}
