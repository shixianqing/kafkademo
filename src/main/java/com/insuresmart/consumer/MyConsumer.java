package com.insuresmart.consumer;

import com.insuresmart.utils.PropsUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Author:shixianqing
 * @Date:2019/7/17 15:55
 * @Description:
 **/
public class MyConsumer {

    public static void main(String[] args) {

        Properties props = new Properties();

        //kafka集群
        props.put("bootstrap.servers", PropsUtils.getString("bootstrap.servers"));
        props.put("group.id",PropsUtils.getString("group.id"));

        //考虑手动提交偏移量
        props.put("enable.auto.commit",PropsUtils.getString("enable.auto.commit"));
        //等待所有副本节点的应答
        props.put("auto.commit.interval.ms",PropsUtils.getString("auto.commit.interval.ms"));
        // 消息发送失败最大尝试次数
        props.put("key.deserializer",PropsUtils.getString("key.deserializer"));
        props.put("value.deserializer",PropsUtils.getString("value.deserializer"));

        //创建消费者
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);

        //订阅主题
        consumer.subscribe(Arrays.asList("first"));

        //读数据
        while (true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            consumer.commitAsync();
            Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
            while (iterator.hasNext()){
                ConsumerRecord<String, String> next = iterator.next();
                long offset = next.offset();
                int partition = next.partition();
                String value = next.value();
                System.out.println("offset："+offset+"，partition："+partition+"，value："+value);
            }
        }
    }
}
