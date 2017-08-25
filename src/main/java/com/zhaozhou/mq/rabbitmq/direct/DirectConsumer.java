package com.zhaozhou.mq.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.zhaozhou.mq.rabbitmq.ChannelFactory;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class DirectConsumer implements Runnable{
    final static String EXCHANGE_NAME = "zhaozhou_direct";

    private int cnt = 0;

    public DirectConsumer() {
    }

    public DirectConsumer(int cnt) {
        this.cnt = cnt;
    }

    public void run(){
        try {
            Channel channel = ChannelFactory.getChannel(EXCHANGE_NAME,"direct");
            if(channel == null){
                System.out.println("get channel fail!");
                return;
            }
            String queneName = "zhaozhou";
            channel.queueDeclare(queneName,false,false,false,null);
            channel.queueBind(queneName, EXCHANGE_NAME, "zhaozhou."+cnt);
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
