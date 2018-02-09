package com.wab.listener;

import com.wab.ipdb.IP;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
/**
 * @author hcq
 * @create 2018-01-29 下午 7:07
 **/

public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        IP.init();
    }
}
