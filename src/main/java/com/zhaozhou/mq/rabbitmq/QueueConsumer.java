package com.zhaozhou.mq.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;

/**
 * Created by zhaozhou on 2017-08-23.
 */
public class QueueConsumer extends  EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void run() {
        try{
            channel.basicConsume(endPointName, true, this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleConsumeOk(String s) {
        System.out.println("Consumer " + s + " registered");
    }

    public void handleCancelOk(String s) {

    }

    public void handleCancel(String s) throws IOException {

    }

    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    public void handleRecoverOk(String s) {

    }

    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        System.out.println("received:" + new String(bytes));
    }
}
