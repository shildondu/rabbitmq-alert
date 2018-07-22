package com.shildon.rabbitmq.alert.handler;

import com.shildon.rabbitmq.alert.JudgeResult;
import com.shildon.rabbitmq.alert.NotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Shildon on 2018/7/17.
 */
public class LogHandler implements NotificationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogHandler.class);

    @Override
    public void onHandle(JudgeResult judgeResult) {
        LOGGER.info("[onHandle] over node size: {}", judgeResult.getOverNodeMap().size());
        LOGGER.info("[onHandle] over queue size: {}", judgeResult.getOverQueueMap().size());
    }

}
