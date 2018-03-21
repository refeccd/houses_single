package com.liaozan.web.ext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/3/18
 */
@Slf4j
@Component
public class MyBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry (BeanDefinitionRegistry registry) throws BeansException {
		log.debug("BeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry");
	}

	@Override
	public void postProcessBeanFactory (ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.debug("BeanDefinitionRegistryPostProcessor...postProcessBeanFactory");
	}
}
