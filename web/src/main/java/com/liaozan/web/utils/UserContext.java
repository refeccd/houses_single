package com.liaozan.web.utils;

import com.liaozan.common.model.User;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
public class UserContext {
	private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

	public static void setUser(User user) {
		USER_HOLDER.set(user);
	}

	public static User getUser() {
		return USER_HOLDER.get();
	}

	public static void remove() {
		USER_HOLDER.remove();
	}
}
