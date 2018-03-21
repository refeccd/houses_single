package com.liaozan.common.model;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
public class Community {
	private Integer id;
	private String cityCode;
	private String cityName;
	private String name;

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getCityCode () {
		return cityCode;
	}

	public void setCityCode (String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName () {
		return cityName;
	}

	public void setCityName (String cityName) {
		this.cityName = cityName;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}
}
