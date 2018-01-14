package com.liaozan.biz.service;

import com.google.common.collect.Lists;
import com.liaozan.biz.mapper.UserMapper;
import com.liaozan.common.model.User;
import com.liaozan.common.utils.BeanHelper;
import com.liaozan.common.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FileService fileService;
	@Autowired
	private MailService mailServer;

	@Transactional(rollbackFor = Exception.class)
	public boolean addAccount(User account) {
		account.setPasswd(HashUtils.encryPassword(account.getPasswd()));
		List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatarFile()));
		if (!imgList.isEmpty()) {
			account.setAvatar(imgList.get(0));
		}
		BeanHelper.setDefaultProp(account, User.class);
		BeanHelper.onInsert(account);
		account.setEnable(0);
		mailServer.registerNotify(account.getEmail());
		return userMapper.insert(account) > 0;
	}

	public boolean enable(String key) {
		return mailServer.enable(key);
	}
}
