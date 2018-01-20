package com.liaozan.biz.service;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.liaozan.biz.mapper.HouseMapper;
import com.liaozan.common.config.WebApplicationPropertiesConfig;
import com.liaozan.common.constants.HouseUserType;
import com.liaozan.common.model.*;
import com.liaozan.common.page.PageData;
import com.liaozan.common.page.PageParams;
import com.liaozan.common.utils.BeanHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liaozan
 * @version 1.0.0
 * @since 2018/1/15
 */
@Service
@EnableConfigurationProperties(WebApplicationPropertiesConfig.class)
public class HouseService {
	@Autowired
	private WebApplicationPropertiesConfig webApplicationPropertiesConfig;
	@Autowired
	private HouseMapper houseMapper;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private MailService mailService;
	@Autowired
	private FileService fileService;

	public PageData<House> queryHouse(House query, PageParams pageParams) {
		List<House> houses = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(query.getName())) {
			Community community = new Community();
			community.setName(query.getName());
			List<Community> communities = houseMapper.selectCommunity(community);
			if (!communities.isEmpty()) {
				query.setCommunityId(communities.get(0).getId());
			}
		}
		houses = queryAndSetImg(query, pageParams);
		Long count = houseMapper.selectPageCount(query);
		return PageData.buildPage(houses, count, pageParams.getPageSize(), pageParams.getPageNum());
	}

	public List<House> queryAndSetImg(House query, PageParams pageParams) {
		List<House> houses = houseMapper.selectPageHouses(query, pageParams);
		houses.forEach(house -> {
			house.setFirstImg(webApplicationPropertiesConfig.getNginxserverprefix() + house.getFirstImg());
			house.setImageList(house.getImageList().stream().map(img -> webApplicationPropertiesConfig.getNginxserverprefix() + img).collect(Collectors.toList()));
			house.setFloorPlanList(house.getFloorPlanList().stream().map(pic -> webApplicationPropertiesConfig.getNginxserverprefix() + pic).collect(Collectors.toList()));
		});
		return houses;
	}

	public House queryOneHouse(Long id) {
		House query = new House();
		query.setId(id);
		List<House> houses = queryAndSetImg(query, PageParams.build(1, 1));
		if (!houses.isEmpty()) {
			return houses.get(0);
		}
		return null;
	}

	public void addHouseMsg(UserMsg userMsg) {
		BeanHelper.onInsert(userMsg);
		houseMapper.insertUserMsg(userMsg);
		User agent = agencyService.getAgentDetail(userMsg.getAgentId());
		mailService.sendMail("来自用户" + userMsg.getEmail() + "的留言", userMsg.getMsg(), agent.getEmail());
	}

	public HouseUser getHouseUser(Long houseId) {
		return houseMapper.selectSaleHouseUser(houseId);
	}

	public List<Community> getAllCommunitys() {
		Community query = new Community();
		return houseMapper.selectCommunity(query);
	}

	public void addHouse(House house, User user) {
		if (CollectionUtils.isNotEmpty(house.getHouseFiles())) {
			String images = Joiner.on(",").join(fileService.getImgPath(house.getHouseFiles()));
			house.setImages(images);
		}
		if (CollectionUtils.isNotEmpty(house.getFloorPlanFiles())) {
			String images = Joiner.on(",").join(fileService.getImgPath(house.getFloorPlanFiles()));
			house.setFloorPlan(images);
		}
		BeanHelper.onInsert(house);
		houseMapper.insert(house);
		bindUser2House(house.getId(), user.getId(), false);
	}

	public void bindUser2House(Long houseId, Long userId, boolean isCollect) {
		HouseUser existUser = houseMapper.selectHouseUser(houseId, userId, isCollect ? HouseUserType.BOOKMARK.value : HouseUserType.SALE.value);
		if (existUser != null) {
			return;
		}
		HouseUser houseUser = new HouseUser();
		houseUser.setHouseId(houseId);
		houseUser.setUserId(userId);
		houseUser.setType(isCollect ? HouseUserType.BOOKMARK.value : HouseUserType.SALE.value);
		BeanHelper.setDefaultProp(houseUser, HouseUser.class);
		BeanHelper.onInsert(houseUser);
		houseMapper.insertHouseUser(houseUser);
	}

	public void updateRating(Long id, Double rating) {
		House house = queryOneHouse(id);
		Double oldRating = house.getRating();
		Double newRating = oldRating.equals(0D) ? rating : Math.min((oldRating + rating) / 2, 5);
		House updateHouse = new House();
		updateHouse.setId(id);
		updateHouse.setRating(newRating);
		BeanHelper.onUpdate(updateHouse);
		houseMapper.updateHouse(updateHouse);
	}

	public void unbindUser2House(Long id, Long userId, HouseUserType type) {
		houseMapper.deleteHouseUser(id, userId, type.value);
	}
}
