package com.wab.config;

import com.wab.listener.ApplicationStartListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hcq
 * @create 2018-01-29 下午 7:16
 **/

@Configuration
public class ListenerConfig {
    @Bean
    public ApplicationStartListener applicationStartListener(){
        return new ApplicationStartListener();
    }
}
