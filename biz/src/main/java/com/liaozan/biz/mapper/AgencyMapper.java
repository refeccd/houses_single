package com.liaozan.biz.mapper;

import java.util.List;

import com.liaozan.common.model.Agency;
import com.liaozan.common.model.User;
import com.liaozan.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AgencyMapper {

	List<Agency> select(Agency agency);

	int insert(Agency agency);

	List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);

	Long selectAgentCount(@Param("user") User user);

}
