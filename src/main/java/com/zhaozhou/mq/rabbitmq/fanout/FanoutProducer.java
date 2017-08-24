package com.zhaozhou.mq.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by zhaozhou on 2017-08-24.
 */
public class FanoutProducer {
    public static void main(String[] argv)
            throws java.io.IOException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("EXCHANGE_NAME", "fanout");


        for(int i = 0; i < 10000; i ++){
            channel.basicPublish(EXCHANGE_NAME, "zhaozhou." + i, null, new String("消息：" + i).getBytes());
            System.out.println("Exchange[" + EXCHANGE_NAME +"]Sent '" + "zhaozhou." + i + "':'" + new String("消息：" + i).getBytes() + "'");
        }


        channel.close();
        connection.close();
    }
}
