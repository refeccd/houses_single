package com.liaozan.web.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/3/18
 */
@Component
@Slf4j
public class MyBeanFactoryPostProcessors implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory (ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.error("BeanFactoryPostProcessor...postProcessBeanFactory");
	}
}
