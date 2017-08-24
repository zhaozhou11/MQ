package com.zhaozhou.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by zhaozhou on 2017-08-23.
 */
public abstract class EndPoint {
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint(String endPointName) throws IOException{
        this.endPointName = endPointName;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("zhaozhou");
        factory.setPassword("zhaozhou");
        factory.setVirtualHost("/");
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(endPointName, false, false, false, null);
    }

    public void close()throws IOException{
        this.channel.close();
        this.connection.close();
    }
}
