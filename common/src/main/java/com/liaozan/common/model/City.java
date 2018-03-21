package com.liaozan.common.model;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
public class City {
	private Integer id;
	private String cityName;
	private String cityCode;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getCityName () {
		return cityName;
	}

	public void setCityName (String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode () {
		return cityCode;
	}

	public void setCityCode (String cityCode) {
		this.cityCode = cityCode;
	}

}
