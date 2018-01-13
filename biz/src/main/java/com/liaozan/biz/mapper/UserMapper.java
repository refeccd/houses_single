package com.liaozan.biz.mapper;

import com.liaozan.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/14
 */
@Mapper
@Repository
public interface UserMapper {

	/**
	 * insert
	 *
	 * @param account entity
	 * @return result
	 */
	int insert(User account);

	/**
	 * delete
	 *
	 * @param email email
	 */
	void delete(String email);
}
