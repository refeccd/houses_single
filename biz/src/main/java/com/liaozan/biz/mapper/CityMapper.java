package com.liaozan.biz.mapper;

import com.liaozan.common.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
@Mapper
@Repository
public interface CityMapper {
	/**
	 * 获取所有城市
	 *
	 * @return list
	 */
	List<City> getAllCitys();
}
