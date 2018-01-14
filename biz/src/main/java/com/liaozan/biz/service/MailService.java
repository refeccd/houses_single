package com.liaozan.biz.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.liaozan.biz.mapper.UserMapper;
import com.liaozan.common.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Service
public class MailService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String from;
	@Value("${domain.name}")
	private String domainName;
	private final Cache<String, String> registerCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES).removalListener(new RemovalListener<String, String>() {
		@Override
		public void onRemoval(RemovalNotification<String, String> notification) {
			userMapper.delete(notification.getValue());
		}
	}).build();

	/**
	 * send email
	 *
	 * @param title title for mail
	 * @param url   content
	 * @param email to
	 */
	private void sendMail(String title, String url, String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(email);
		message.setText(url);
		message.setSubject(title);
		mailSender.send(message);
	}

	@Async
	public void registerNotify(String email) {
		String randomKey = RandomStringUtils.randomAlphabetic(10);
		registerCache.put(randomKey, email);
		String url = "http://" + domainName + "/accounts/vevify?key=" + randomKey;
		sendMail("激活邮件", url, email);
	}

	public boolean enable(String key) {
		String email = registerCache.getIfPresent(key);
		if (StringUtils.isBlank(email)) {
			return false;
		}
		User updateUser = new User();
		updateUser.setEnable(1);
		updateUser.setEmail(email);
		userMapper.update(updateUser);
		registerCache.invalidate(email);
		return true;
	}
}
