package com.liaozan.biz.service;

import com.liaozan.biz.mapper.CityMapper;
import com.liaozan.common.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;

	public List<City> getAllCitys() {
		return cityMapper.getAllCitys();
	}

}
