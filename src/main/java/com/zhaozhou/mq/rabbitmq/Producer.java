package com.zhaozhou.mq.rabbitmq;

import java.io.IOException;

/**
 * Created by zhaozhou on 2017-08-23.
 */
public class Producer extends  EndPoint {
    public Producer(String endPointName) throws IOException {
        super(endPointName);
    }

    public void sendMessage(String data) throws IOException{
        channel.basicPublish("", endPointName, null, data.getBytes());
    }
}
