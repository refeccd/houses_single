package com.liaozan.biz.mapper;

import com.liaozan.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	int insert (User account);

	/**
	 * delete
	 *
	 * @param email email
	 * @return int result
	 */
	int delete (String email);

	/**
	 * update user
	 *
	 * @param updateUser user
	 * @return int result
	 */
	int update (User updateUser);

	/**
	 * get user
	 *
	 * @param user user
	 * @return list of user
	 */
	List<User> selectUsersByQuery (User user);
}
