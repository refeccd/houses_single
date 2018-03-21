package com.liaozan.biz.mapper;

import com.liaozan.common.model.Blog;
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
public interface BlogMapper {

	/**
	 * select blog
	 *
	 * @param query  param
	 * @param params pageParams
	 * @return result
	 */
	List<Blog> selectBlog(@Param("blog") Blog query, @Param("pageParams") PageParams params);

	/**
	 * selectBlogCount
	 *
	 * @param query param
	 * @return result
	 */
	Long selectBlogCount(Blog query);

}
