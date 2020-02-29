package com.fangzy.rocketmq_java_simple.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer2 {
    public static void main(String[] args)throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("xoxogp");

        // 设置nameserver地址
        producer.setNamesrvAddr("192.168.152.139:9876");
        producer.start();

        // 异步可靠消息
        // 不会阻塞，等待broker的确认
        //采用事件监听方式接受broker返回的确认
        Message message = new Message("myTopic001", "xxoo".getBytes());


        producer.send(message,new SendCallback() {

            @Override
            public void onSuccess(SendResult sendResult) {
                // TODO Auto-generated method stub
                System.out.println("消息发送成功。。。");
                System.out.println("sendResult ：" + sendResult);

            }

            @Override
            public void onException(Throwable e) {

                // 如果发生异常 case 异常，尝试重投
                // 或者调整业务逻辑

                e.printStackTrace();
                System.out.println("发送异常");
            }
        });


//		producer.shutdown();
        System.out.println("已经停机");

    }
}
