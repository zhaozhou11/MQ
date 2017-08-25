package com.zhaozhou.mq.rabbitmq.direct;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class DirectMain {

    public static void main(String[] args) throws java.lang.InterruptedException{

        for(int i = 0; i < 5 ;i ++){
            Thread thread = new Thread(new DirectConsumer(i));
            thread.start();
        }

        Thread.sleep(2000);


        try {
            DirectProducer producer = new DirectProducer();
            producer.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
