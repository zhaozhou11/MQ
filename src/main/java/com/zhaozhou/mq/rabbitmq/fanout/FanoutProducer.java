package com.zhaozhou.mq.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.zhaozhou.mq.rabbitmq.ChannelFactory;

/**
 * Created by zhaozhou on 2017-08-24.
 */
public class FanoutProducer {

    final static String EXCHANGE_NAME = "zhaozhou_fanout";
    public  void start()
            throws java.io.IOException,java.lang.InterruptedException{

        Channel channel = ChannelFactory.getChannel(EXCHANGE_NAME,"fanout");
        if(channel == null){
            System.out.println("get channel fail!");
            return;
        }


        for(int i = 0; i < 10000; i ++){
            channel.basicPublish(EXCHANGE_NAME, "zhaozhou." + i, null, new String("消息：" + i).getBytes());
            System.out.println("Exchange[" + EXCHANGE_NAME +"]Sent '" + "zhaozhou." + i + "':'" + new String("消息：" + i));
        }

        Thread.sleep(30 * 1000);
        ChannelFactory.close(channel);
    }
}
