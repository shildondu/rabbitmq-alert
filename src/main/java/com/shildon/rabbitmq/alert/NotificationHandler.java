package com.shildon.rabbitmq.alert;

/**
 * 超过阈值处理逻辑接口
 * Created by Shildon on 2018/7/17.
 */
public interface NotificationHandler {

	void onHandle(JudgeResult judgeResult);

}
