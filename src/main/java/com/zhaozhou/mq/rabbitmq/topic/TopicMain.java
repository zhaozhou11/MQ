package com.zhaozhou.mq.rabbitmq.topic;

import com.zhaozhou.mq.rabbitmq.direct.DirectConsumer;
import com.zhaozhou.mq.rabbitmq.direct.DirectProducer;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class TopicMain {

    public static void main(String[] args) throws InterruptedException{

        for(int i = 1; i < 2 ;i ++){
            Thread thread = new Thread(new TopicConsumer(i));
            thread.start();
        }

        Thread.sleep(2000);


        try {
            TopicProducer producer = new TopicProducer();
            producer.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
