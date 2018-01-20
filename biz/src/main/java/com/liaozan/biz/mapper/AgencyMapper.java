package com.liaozan.biz.mapper;

import com.liaozan.common.model.Agency;
import com.liaozan.common.model.User;
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
public interface AgencyMapper {

	/**
	 * select
	 *
	 * @param agency param
	 * @return result
	 */
	List<Agency> select(Agency agency);

	/**
	 * insert
	 *
	 * @param agency param
	 * @return result
	 */
	int insert(Agency agency);

	/**
	 * selectAgent
	 *
	 * @param user       user
	 * @param pageParams pageParams
	 * @return result
	 */
	List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

	/**
	 * selectAgentCount
	 *
	 * @param user user
	 * @return result
	 */
	Long selectAgentCount(@Param("user") User user);

}
