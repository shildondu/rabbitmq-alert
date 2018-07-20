package com.shildon.rabbitmq.alert.handler;

import com.shildon.rabbitmq.alert.JudgeResult;
import com.shildon.rabbitmq.alert.NotificationHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Shildon on 2018/7/17.
 */
@Slf4j
public class LogHandler implements NotificationHandler {

	@Override
	public void onHandle(JudgeResult judgeResult) {
		log.info("[onHandle] over node size: {}", judgeResult.getOverNodeMap().size());
		log.info("[onHandle] over queue size: {}", judgeResult.getOverQueueMap().size());
	}

}
