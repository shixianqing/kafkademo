package com.insuresmart.producer;

import com.insuresmart.utils.PropsUtils;
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
        props.put("bootstrap.servers", PropsUtils.getString("bootstrap.servers"));
        props.put("key.serializer",PropsUtils.getString("key.serializer"));
        props.put("value.serializer",PropsUtils.getString("value.serializer"));
        //等待所有副本节点的应答
        props.put("acks",PropsUtils.getString("acks"));
        // 消息发送失败最大尝试次数
        props.put("retries",PropsUtils.getString("retries"));
        //一次批处理消息大小
        props.put("batch.size",PropsUtils.getString("batch.size"));
        //请求延时
        props.put("linger.ms",PropsUtils.getString("linger.ms"));
        //发送缓存区内存大小
        props.put("buffer.memory",PropsUtils.getString("buffer.memory"));

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);

        for (int i=0; i< 10; i++){
            producer.send(new ProducerRecord<String, String>("first","hello"+i));
        }

        producer.close();
    }
}


