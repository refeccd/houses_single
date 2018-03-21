package com.liaozan.common.exception;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/3/21
 */
public class HouseException extends RuntimeException {
	private static final long serialVersionUID = 2042692859232996625L;

	public HouseException (String message) {
		super(message);
	}
}
