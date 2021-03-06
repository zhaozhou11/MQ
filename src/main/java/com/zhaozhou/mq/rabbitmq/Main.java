package com.zhaozhou.mq.rabbitmq;

import java.io.IOException;

/**
 * Created by zhaozhou on 2017-08-23.
 */
public class Main {
    public static void main(String[] args){
        try{
            QueueConsumer consumer = new QueueConsumer("queue");
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();

            Producer producer = new Producer("queue");

            Long startTs = System.currentTimeMillis();
            for(int i = 0; i < 1000000; i ++){
                producer.sendMessage("I'm " + i);
                System.out.println("Producer send " + i);
            }

            System.out.println("total seconds=" + (System.currentTimeMillis() - startTs)/1000);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
