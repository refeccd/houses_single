package com.liaozan.web.ext;

import com.liaozan.web.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/3/18
 */
@Component
@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent (ApplicationReadyEvent event) {
        log.debug("{}", event.getApplicationContext().getBean(UserController.class));
    }
}
