package com.liaozan.biz.service;

import com.liaozan.biz.mapper.AgencyMapper;
import com.liaozan.common.config.WebApplicationPropertiesConfig;
import com.liaozan.common.model.User;
import com.liaozan.common.page.PageData;
import com.liaozan.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Service
@EnableConfigurationProperties(WebApplicationPropertiesConfig.class)
public class AgencyService {
	@Autowired
	private AgencyMapper agencyMapper;
	@Autowired
	private WebApplicationPropertiesConfig webApplicationPropertiesConfig;

	public User getAgentDetail(Long userId) {
		User user = new User();
		user.setId(userId);
		user.setType(2);
		List<User> users = agencyMapper.selectAgent(user, PageParams.build(1, 1));
		setImg(users);
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	public void setImg(List<User> users) {
		users.forEach(user -> {
			user.setAvatar(webApplicationPropertiesConfig.getNginxserverprefix() + user.getAvatar());
		});
	}

	public PageData<User> getAllAgent(PageParams pageParams) {
		User user = new User();
		List<User> agents = agencyMapper.selectAgent(user, pageParams);
		setImg(agents);
		Long agentCount = agencyMapper.selectAgentCount(user);
		return PageData.buildPage(agents, agentCount, pageParams.getPageSize(), pageParams.getPageNum());
	}
}
