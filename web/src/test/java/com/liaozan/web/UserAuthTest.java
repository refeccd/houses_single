package com.liaozan.web;

import com.liaozan.biz.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserAuthTest {

	@Autowired
	private UserService userService;

	@Test
	public void testAuth() {
		Assert.assertNotNull(userService.auth("378024053@qq.com", "111111"));
	}

}
