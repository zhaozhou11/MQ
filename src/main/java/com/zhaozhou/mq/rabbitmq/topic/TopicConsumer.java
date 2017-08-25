package com.zhaozhou.mq.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.zhaozhou.mq.rabbitmq.ChannelFactory;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class TopicConsumer implements Runnable{
    final static String EXCHANGE_NAME = "zhaozhou_topic";

    private int cnt = 0;

    public TopicConsumer() {
    }

    public TopicConsumer(int cnt) {
        this.cnt = cnt;
    }

    public void run(){
        try {
            Channel channel = ChannelFactory.getChannel(EXCHANGE_NAME,"topic");
            if(channel == null){
                System.out.println("get channel fail!");
                return;
            }
            String queneName = "zhaozhou1";
            channel.queueDeclare(queneName,false,false,false,null);
            String routeKey = "#";
            if(cnt == 0)
                routeKey = "zhaozhou.*";
            else{
                routeKey = "*." + cnt;
            }
            channel.queueBind(queneName, EXCHANGE_NAME, routeKey);
            System.out.println("queue:" + queneName + "routeKey:" + routeKey);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queneName, true, consumer);

            while (true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String msg = new String(delivery.getBody());
                String key = delivery.getEnvelope().getRoutingKey();

                System.out.println("routeKey:" + key + "msg:" + msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
