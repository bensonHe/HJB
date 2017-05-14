package com.hjb.spider.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hjb.component.util.DateUtils;
import org.hjb.component.util.DecimalUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.hjb.spider.App;

import com.hjb.spider.entity.HourseInfo;

@Component
public class LianjiaResource {
	public static final Logger LOG = LoggerFactory.getLogger(App.class);

	public List<HourseInfo> getNewPub(int page) {
		List<HourseInfo> listHouser = new ArrayList<HourseInfo>();
		Document doc = null;

		try {
			doc = Jsoup.connect("http://gz.lianjia.com/ershoufang/pg" + page + "co32/").timeout(3000)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
					.ignoreContentType(true).get();

			Elements infos = doc.getElementsByClass("sellListContent").first().select("li");
			for (Element info : infos) {
				HourseInfo house = new HourseInfo();
				house.setImage(info.select(".lj-lazy").attr("data-original"));
				house.setHref(info.select(".title").select("a").attr("href"));
				house.setTitle(info.select(".title").select("a").text());
				house.setTotalPrice(DecimalUtil.getDecimal(info.select(".totalPrice").select("span").text()));
				house.setUnitPrice(DecimalUtil.getDecimal(info.select(".unitPrice").attr("data-price")));
				house.setHourseId(info.select(".unitPrice").attr("data-hid"));
				String houseInfo = info.select(".houseInfo").text();
				String houseInfos[] = houseInfo.split(" | ");
				house.setXiaoqu(houseInfos[0]);
				house.setHuxing(houseInfos[2]);
				house.setSize(houseInfos[4]);
				house.setTurned(houseInfos[6]);
				house.setFirstTotalPrice(house.getTotalPrice());
				house.setPosition(info.select(".positionInfo").get(0).select("a").text());
				if (houseInfos.length > 10) {
					house.setLift(houseInfos[10]);
				}
				Date now = DateUtils.getNow();
				house.setCreateTime(now);
				house.setUpdateTime(now);
				listHouser.add(house);

			}
		} catch (IOException e) {
			LOG.error("next ", e);
		}
		return listHouser;

	}

	public BigDecimal catchOnePrice(String hourseId) throws IOException {
		Document doc = Jsoup.connect("http://gz.lianjia.com/ershoufang/GZ0002595821.html").timeout(3000)
				.userAgent(
						"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50")
				.ignoreContentType(true).get();
		String total = doc.select(".price").select(".total").text();
		BigDecimal totalPrice = new BigDecimal(total);
		return totalPrice;

	}

}
