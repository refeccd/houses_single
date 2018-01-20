package com.liaozan.biz.mapper;

import com.liaozan.common.model.Comment;
import com.liaozan.common.model.Community;
import com.liaozan.common.model.House;
import com.liaozan.common.model.UserMsg;
import com.liaozan.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Mapper
public interface CommentMapper {

	/**
	 * selectHouse
	 *
	 * @param query      query
	 * @param pageParams pageParams
	 * @return result
	 */
	List<House> selectHouse(@Param("house") House query, @Param("pageParams") PageParams pageParams);

	/**
	 * selectHouseCount
	 *
	 * @param query query
	 * @return result
	 */
	Long selectHouseCount(@Param("house") House query);

	/**
	 * selectCommunity
	 *
	 * @param community param
	 * @return result
	 */
	List<Community> selectCommunity(Community community);

	/**
	 * insertUserMsg
	 *
	 * @param userMsg userMsg
	 * @return result
	 */
	int insertUserMsg(UserMsg userMsg);

	/**
	 * updateHouse
	 *
	 * @param house house
	 * @return result
	 */
	int updateHouse(House house);

	/**
	 * insert
	 *
	 * @param comment comment
	 * @return result
	 */
	int insert(Comment comment);

	/**
	 * selectComments
	 *
	 * @param houseId houseId
	 * @param size    size
	 * @return result
	 */
	List<Comment> selectComments(@Param("houseId") long houseId, @Param("size") int size);

	/**
	 * selectBlogComments
	 *
	 * @param blogId blogId
	 * @param size   size
	 * @return result
	 */
	List<Comment> selectBlogComments(@Param("blogId") long blogId, @Param("size") int size);

}

