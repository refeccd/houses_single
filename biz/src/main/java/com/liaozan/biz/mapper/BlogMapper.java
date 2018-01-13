package com.liaozan.biz.mapper;

import java.util.List;

import com.liaozan.common.model.Blog;
import com.liaozan.common.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogMapper {

	public List<Blog> selectBlog(@Param("blog") Blog query, @Param("pageParams") PageParams params);

	public Long selectBlogCount(Blog query);

}
