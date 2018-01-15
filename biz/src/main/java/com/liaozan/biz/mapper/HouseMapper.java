package com.liaozan.biz.mapper;

import com.liaozan.common.model.*;
import com.liaozan.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Mapper
@Repository
public interface HouseMapper {

	List<House> selectPageHouses(@Param("house") House house, @Param("pageParams") PageParams pageParams);

	Long selectPageCount(@Param("house") House query);

	int insert(User account);

	List<Community> selectCommunity(Community community);

	int insert(House house);

	HouseUser selectHouseUser(@Param("userId") Long userId, @Param("id") Long houseId, @Param("type") Integer integer);

	HouseUser selectSaleHouseUser(@Param("id") Long houseId);

	int insertHouseUser(HouseUser houseUser);

	int insertUserMsg(UserMsg userMsg);

	int updateHouse(House updateHouse);

	int downHouse(Long id);

	int deleteHouseUser(@Param("id") Long id, @Param("userId") Long userId, @Param("type") Integer value);

}
