package com.hjb.spider.db;

import java.util.List;
import java.util.Map;

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
		jdbcTemplate.update("insert into house_info values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { hourseInfo.getHourseId(), hourseInfo.getTitle(), hourseInfo.getTotalPrice(),
						hourseInfo.getUnitPrice(), hourseInfo.getFirstTotalPrice(), hourseInfo.getLastTotalPrice(),
						hourseInfo.getChangePrice(), hourseInfo.getPosition(), hourseInfo.getXiaoqu(),
						hourseInfo.getHuxing(), hourseInfo.getSize(), hourseInfo.getTurned(), hourseInfo.getRemark(),
						hourseInfo.getLift(), hourseInfo.getImage(), hourseInfo.getHref(), hourseInfo.getCreateTime(),
						hourseInfo.getUpdateTime() });
	}

	public List<Map<String, Object>> listAll() {
		return jdbcTemplate.queryForList("select * from house_info");
	}

	public Map<String, Object> selectByHouseId(String houseId) {
		List<Map<String, Object>> listData = jdbcTemplate.queryForList("select * from house_info where house_id=?",
				new Object[] { houseId });
		if (listData.isEmpty()) {
			return null;
		}
		return listData.get(0);

	}

	public void updateByHouseId(HourseInfo hourseInfo) {
		jdbcTemplate.update(
				"update house_info set total_price=?,last_total_price=?,change_price=?,update_time=? where house_id=?",
				new Object[] { hourseInfo.getTotalPrice(), hourseInfo.getLastTotalPrice(), hourseInfo.getChangePrice(),
						DateUtils.getNow(), hourseInfo.getHourseId() });
	}
}
