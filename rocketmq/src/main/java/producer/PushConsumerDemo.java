/*
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package producer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * PushConsumerDemo
 *
 * @author cheqianzi.ygj
 * @email: cheqianzi@2dfire.com
 * @desc:
 * @since 2017-12-13
 */
public class PushConsumerDemo {
	public static void main(String[] args) {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumers");
		consumer.setNamesrvAddr("10.211.55.5:9876");


		try {
			//订阅PushTopic下的tag为消息
			consumer.subscribe("orders",null);
			//程序第一次启动从西阿西对头取数据
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			consumer.registerMessageListener(
					new MessageListenerConcurrently() {
						@Override
						public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
							Message msg = msgs.get(0);
							byte[] aa = msg.getBody();
							System.out.println(new java.lang.String(aa));

							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
			);
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}

	}
}