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

	List<Agency> select(Agency agency);

	int insert(Agency agency);

	List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

	Long selectAgentCount(@Param("user") User user);

}
