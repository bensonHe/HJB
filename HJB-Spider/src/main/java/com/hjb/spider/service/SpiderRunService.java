package com.hjb.spider.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjb.spider.db.HouseInfoDao;
import com.hjb.spider.entity.HourseInfo;
import com.hjb.spider.resource.LianjiaResource;

@Service
public class SpiderRunService {
	@Autowired
	private LianjiaResource resource;

	public static final Logger logger = LoggerFactory.getLogger(SpiderRunService.class);

	@Autowired
	private HouseInfoDao houseInfoDao;

	public void startCatch(int page) {
		List<HourseInfo> listHouses = resource.getNewPub(page);
		for (HourseInfo hourseInfo : listHouses) {
			Map<String, Object> data = houseInfoDao.selectByHouseId(hourseInfo.getHourseId());
			// if house record exist
			if (data == null) {
				houseInfoDao.insert(hourseInfo);
			} else {
				BigDecimal firstTotalPrice = (BigDecimal) data.get("first_total_price");
				BigDecimal lastTotalPrice = (BigDecimal) data.get("total_price");
				if (lastTotalPrice.compareTo(hourseInfo.getTotalPrice()) != 0) {
					hourseInfo.setChangePrice(hourseInfo.getTotalPrice().subtract(firstTotalPrice));
					hourseInfo.setLastTotalPrice(lastTotalPrice);
					houseInfoDao.updateByHouseId(hourseInfo);
				}
			}
		}
	}

}
