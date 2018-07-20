package com.shildon.rabbitmq.alert;

import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.NodeInfo;
import com.rabbitmq.http.client.domain.QueueInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;

/**
 * RabbitMQ告警通知入口逻辑
 * Created by Shildon on 2018/7/18.
 */
@Slf4j
public class RabbitMQAlert {

	private NotificationHandlerChain notificationHandlerChain;

	public void doAlert() {
		String apiUrl = PropertyValue.RABBITMQ_ADMIN_API_URL.getValue();
		String username = PropertyValue.RABBITMQ_ADMIN_USERNAME.getValue();
		String password = PropertyValue.RABBITMQ_ADMIN_PASSWORD.getValue();
		try {
			Client client = new Client(apiUrl, username, password);

			JudgeResult judgeResult = new JudgeResult();

			client.getNodes()
					.parallelStream()
					.forEach(nodeInfo -> {
						for (BiConsumer<NodeInfo, JudgeResult> judgement : JudgementHolder.NODE_JUDGEMENTS) {
							judgement.accept(nodeInfo, judgeResult);
						}
					});

			client.getQueues()
					.parallelStream()
					.forEach(queueInfo -> {
						for (BiConsumer<QueueInfo, JudgeResult> judgement : JudgementHolder.QUEUE_JUDGEMENTS) {
							judgement.accept(queueInfo, judgeResult);
						}
					});

			notificationHandlerChain.process(judgeResult);
		} catch (Exception e) {
			log.info("[doAlert] error, api url: {}, admin username: {}, admin password: {}", apiUrl, username, password, e);
		}
	}

	public void setNotificationHandlerChain(NotificationHandlerChain notificationHandlerChain) {
		this.notificationHandlerChain = notificationHandlerChain;
	}

}
