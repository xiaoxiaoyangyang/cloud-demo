package com.yangyang.log.config;
import com.yangyang.log.controller.LogConsumer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author gzy
 * @date 2019/7/17 18:00
 */
@Configuration
@AutoConfigureAfter(RedisConfiguration.class)
public class RedisConfiguration {
    @Bean
    RedisMessageListenerContainer container(LettuceConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫chat 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic("testLog"));
        return container;
    }


    /**
     * 利用反射来创建监听到消息之后的执行方法
     */
    @Bean
    MessageListenerAdapter listenerAdapter(LogConsumer logConsumer) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        //这里2个参数和四一致
        return new MessageListenerAdapter(logConsumer, "receiveMessage");
    }
}
