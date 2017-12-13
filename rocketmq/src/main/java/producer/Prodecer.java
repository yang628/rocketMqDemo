/*
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package producer;


import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Prodecer
 *
 * @author cheqianzi.ygj
 * @email: cheqianzi@2dfire.com
 * @desc:
 * @since 2017-12-13
 */
public class Prodecer {


	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("group");
		producer.setNamesrvAddr("10.211.55.5:9876");
		producer.start();
		for (int i = 0; i < 100; i++) {
			Message msg = new Message("orders",("order"+i).getBytes());
			SendResult result = producer.send(msg);
			System.out.println(result);
			System.out.println(msg+"   send out");
			Thread.sleep(500);
		}
		producer.shutdown();

	}

}