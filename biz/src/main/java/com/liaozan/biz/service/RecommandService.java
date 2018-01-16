package com.liaozan.biz.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.liaozan.common.model.House;
import com.liaozan.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/16
 */
@Service
public class RecommandService {

	private static final String HOT_HOUSE_KEY = "hot_house";

	@Autowired
	private HouseService houseService;

	public void increase(Long id) {
		Jedis jedis = new Jedis();
		jedis.zincrby(HOT_HOUSE_KEY, 1.0D, id + "");
		jedis.zremrangeByRank(HOT_HOUSE_KEY, 10, -1);
		jedis.close();
	}

	public List<Long> getHot() {
		Jedis jedis = new Jedis();
		Set<String> idSet = jedis.zrevrange(HOT_HOUSE_KEY, 0, -1);
		jedis.close();
		return idSet.stream().map(Long::parseLong).collect(Collectors.toList());
	}

	public List<House> getHotHouse(Integer size) {
		House query = new House();
		List<Long> list = getHot();
		list.subList(0, Math.min(list.size(), size));
		if (list.isEmpty()) {
			return Lists.newArrayList();
		}
		query.setIds(list);
		List<House> houses = houseService.queryAndSetImg(query, PageParams.build(size, 1));
		Ordering<House> houseSort = Ordering.natural().onResultOf(hs -> list.indexOf(hs.getId()));
		return houseSort.sortedCopy(houses);
	}

	public List<House> getLastest() {
		House query = new House();
		query.setSort("create_time");
		return houseService.queryAndSetImg(query,PageParams.build(8,1));
	}
}
