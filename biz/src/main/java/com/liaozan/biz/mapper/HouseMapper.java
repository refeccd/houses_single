package com.liaozan.biz.mapper;

import com.liaozan.common.model.Community;
import com.liaozan.common.model.House;
import com.liaozan.common.model.HouseUser;
import com.liaozan.common.model.UserMsg;
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

	/**
	 * selectPageHouses
	 *
	 * @param house      house
	 * @param pageParams pageParam
	 * @return result
	 */
	List<House> selectPageHouses (@Param("house") House house, @Param("pageParams") PageParams pageParams);

	/**
	 * selectPageCount
	 *
	 * @param query query
	 * @return result
	 */
	Long selectPageCount (@Param("house") House query);

	/**
	 * selectCommunity
	 *
	 * @param community community
	 * @return result
	 */
	List<Community> selectCommunity (Community community);

	/**
	 * insert
	 *
	 * @param house house
	 * @return result
	 */
	int insert (House house);

	/**
	 * selectHouseUser
	 *
	 * @param userId  userId
	 * @param houseId houseId
	 * @param integer integer
	 * @return result
	 */
	HouseUser selectHouseUser (@Param("userId") Long userId, @Param("id") Long houseId, @Param("type") Integer integer);

	/**
	 * selectSaleHouseUser
	 *
	 * @param houseId houseId
	 * @return result
	 */
	HouseUser selectSaleHouseUser (@Param("id") Long houseId);

	/**
	 * insertHouseUser
	 *
	 * @param houseUser houseUser
	 * @return result
	 */
	int insertHouseUser (HouseUser houseUser);

	/**
	 * insertUserMsg
	 *
	 * @param userMsg userMsg
	 * @return result
	 */
	int insertUserMsg (UserMsg userMsg);

	/**
	 * updateHouse
	 *
	 * @param updateHouse updateHouse
	 * @return result
	 */
	int updateHouse (House updateHouse);

	/**
	 * downHouse
	 *
	 * @param id id
	 * @return result
	 */
	int downHouse (Long id);

	/**
	 * deleteHouseUser
	 *
	 * @param id     id
	 * @param userId userId
	 * @param type   type
	 * @return result
	 */
	int deleteHouseUser (@Param("id") Long id, @Param("userId") Long userId, @Param("type") Integer type);

}
