package com.zhaozhou.mq.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.zhaozhou.mq.rabbitmq.ChannelFactory;

/**
 * Created by zhaozhou on 2017-08-24.
 */
public class FanoutConsumer implements Runnable{

    final static String EXCHANGE_NAME = "zhaozhou_fanout";


    public void run(){
        try {
            Channel channel = ChannelFactory.getChannel(EXCHANGE_NAME,"fanout");
            if(channel == null){
                System.out.println("get channel fail!");
                return;
            }
            String queneName = channel.queueDeclare().getQueue();
            channel.queueBind(queneName, EXCHANGE_NAME, "");
            System.out.println("queue:" + queneName);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queneName, true, consumer);

            while (true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String msg = new String(delivery.getBody());
                String routeKey = delivery.getEnvelope().getRoutingKey();

                System.out.println("routeKey:" + routeKey + "msg:" + msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
