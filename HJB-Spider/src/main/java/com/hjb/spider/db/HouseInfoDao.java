package com.hjb.spider.db;

import org.hjb.component.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hjb.spider.entity.HourseInfo;

@Repository
public class HouseInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(HourseInfo hourseInfo) {
		jdbcTemplate.update("insert into house_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { hourseInfo.getHourseId(), hourseInfo.getTitle(), hourseInfo.getTotalPrice(),
						hourseInfo.getUnitPrice(), hourseInfo.getPosition(), hourseInfo.getXiaoqu(),
						hourseInfo.getHuxing(), hourseInfo.getSize(), hourseInfo.getTurned(), hourseInfo.getRemark(),
						hourseInfo.getLift(), hourseInfo.getImage(), hourseInfo.getHref(), hourseInfo.getCreateTime(),
						hourseInfo.getUpdateTime() });
	}
}
