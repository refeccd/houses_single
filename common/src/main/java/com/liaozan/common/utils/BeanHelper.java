package com.liaozan.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
public class BeanHelper {

	private static final String UPDATE_TIME_KEY = "updateTime";

	private static final String CREATE_TIME_KEY = "createTime";

	public static <T> void setDefaultProp(T target, Class<T> clazz) {
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			String fieldName = propertyDescriptor.getName();
			Object value;
			try {
				value = PropertyUtils.getProperty(target, fieldName);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException("can not set property  when get for " + target + " and clazz " + clazz + " field " + fieldName);
			}
			if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
				try {
					PropertyUtils.setProperty(target, fieldName, "");
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new RuntimeException("can not set property when set for " + target + " and clazz " + clazz + " field " + fieldName);
				}
			} else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
				try {
					BeanUtils.setProperty(target, fieldName, "0");
				} catch (Exception e) {
					throw new RuntimeException("can not set property when set for " + target + " and clazz " + clazz + " field " + fieldName);
				}
			}
		}
	}

	public static <T> void onUpdate(T target) {
		try {
			PropertyUtils.setProperty(target, UPDATE_TIME_KEY, System.currentTimeMillis());
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {

		}
	}

	private static <T> void innerDefault(T target, Class<?> clazz, PropertyDescriptor[] descriptors) {
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			String fieldName = propertyDescriptor.getName();
			Object value;
			try {
				value = PropertyUtils.getProperty(target, fieldName);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException("can not set property  when get for " + target + " and clazz " + clazz + " field " + fieldName);
			}
			if (String.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
				try {
					PropertyUtils.setProperty(target, fieldName, "");
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new RuntimeException("can not set property when set for " + target + " and clazz " + clazz + " field " + fieldName);
				}
			} else if (Number.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
				try {
					BeanUtils.setProperty(target, fieldName, "0");
				} catch (Exception e) {
					throw new RuntimeException("can not set property when set for " + target + " and clazz " + clazz + " field " + fieldName);
				}
			} else if (Date.class.isAssignableFrom(propertyDescriptor.getPropertyType()) && value == null) {
				try {
					BeanUtils.setProperty(target, fieldName, new Date(0));
				} catch (Exception e) {
					throw new RuntimeException("can not set property when set for " + target + " and clazz " + clazz + " field " + fieldName);
				}
			}
		}
	}

	public static <T> void onInsert(T target) {
		Class<?> clazz = target.getClass();
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
		innerDefault(target, clazz, descriptors);
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		try {
			PropertyUtils.setProperty(target, UPDATE_TIME_KEY, date);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {

		}
		try {
			PropertyUtils.setProperty(target, CREATE_TIME_KEY, date);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {

		}
	}

}
