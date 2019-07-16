package com.insuresmart.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author:sxq
 * @Date: 2019/7/16
 * @Description: 生产者
 */
public class CustomerProducer {


    public static void main(String[] args) {
        Properties props = new Properties();

        //kafka集群
        props.put("bootstrap.servers","192.168.220.128:9092,192.168.220.129:9092,192.168.220.134:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        //等待所有副本节点的应答
        props.put("acks","all");
        // 消息发送失败最大尝试次数
        props.put("retries","0");
        //一次批处理消息大小
        props.put("batch.size","16384");
        //请求延时
        props.put("linger.ms","5");
        //发送缓存区内存大小
        props.put("buffer.memory","33554432");

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);

        for (int i=0; i< 10; i++){
            producer.send(new ProducerRecord<String, String>("second","hello"+i));
        }

        producer.close();
    }
}


