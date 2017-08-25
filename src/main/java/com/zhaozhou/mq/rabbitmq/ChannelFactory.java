package com.zhaozhou.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by zhaozhou on 2017-08-25.
 */
public class ChannelFactory {
    public static Channel getChannel(String exchange, String exchageType){

        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchange, exchageType);

            return channel;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void close(Channel channel){
        if(channel != null){
            try {
                channel.getConnection().close();
                channel.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
