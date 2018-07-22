package com.shildon.rabbitmq.alert;

import com.rabbitmq.http.client.domain.NodeInfo;
import com.rabbitmq.http.client.domain.QueueInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 阈值判断结果。如果某个属性超过设置的阈值，则将其加入该容器
 * Created by Shildon on 2018/7/18.
 */
public class JudgeResult {

    private Map<PropertyValue, List<QueueInfo>> overQueueMap = new HashMap<>();
    private Map<PropertyValue, List<NodeInfo>> overNodeMap = new HashMap<>();

    {
        PropertyValue[] propertyValues = PropertyValue.values();
        Stream.of(propertyValues)
                .forEach(propertyValue -> {
                    if (propertyValue.getCode().startsWith("node")) {
                        overNodeMap.put(propertyValue, new LinkedList<>());
                    }
                    if (propertyValue.getCode().startsWith("queue")) {
                        overQueueMap.put(propertyValue, new LinkedList<>());
                    }
                });
    }

    public List<NodeInfo> getOverNodes(PropertyValue propertyValue) {
        return overNodeMap.get(propertyValue);
    }

    public List<QueueInfo> getOverQueues(PropertyValue propertyValue) {
        return overQueueMap.get(propertyValue);
    }

    public Map<PropertyValue, List<QueueInfo>> getOverQueueMap() {
        return overQueueMap;
    }

    public Map<PropertyValue, List<NodeInfo>> getOverNodeMap() {
        return overNodeMap;
    }

    @Override
    public String toString() {
        return "JudgeResult{" +
                "overQueueMap=" + overQueueMap +
                ", overNodeMap=" + overNodeMap +
                '}';
    }
}
