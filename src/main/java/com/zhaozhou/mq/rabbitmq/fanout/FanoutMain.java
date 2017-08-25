package com.zhaozhou.mq.rabbitmq.fanout;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class FanoutMain {

    public static void main(String[] args){

        for(int i = 0; i < 5 ;i ++){
            Thread thread = new Thread(new FanoutConsumer());
            thread.start();
        }



        try {
            FanoutProducer producer = new FanoutProducer();
            producer.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
