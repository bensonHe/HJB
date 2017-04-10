package com.hjb.spider.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hjb.component.util.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hjb.spider.App;
import com.hjb.spider.db.HouseInfoDao;
import com.hjb.spider.entity.HourseInfo;

@Component
public class Lianjia {
	public static final Logger LOG = Logger.getLogger(App.class);
	@Autowired
	private HouseInfoDao houseInfoDao;

	public void catchNew(int page) {

		Document doc = null;

		try {
			doc = Jsoup.connect("http://gz.lianjia.com/ershoufang/pg" + page + "co32/").timeout(3000)
					.userAgent(
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50")
					.ignoreContentType(true).get();

			Elements infos = doc.getElementsByClass("sellListContent").first().select("li");
			for (Element info : infos) {
				HourseInfo house = new HourseInfo();
				house.setImage(info.select(".lj-lazy").attr("data-original"));
				house.setHref(info.select(".title").select("a").attr("href"));
				house.setTitle(info.select(".title").select("a").text());
				house.setTotalPrice(info.select(".totalPrice").select("span").text());
				house.setUnitPrice(info.select(".unitPrice").attr("data-price"));
				house.setHourseId(info.select(".unitPrice").attr("data-hid"));
				String houseInfo = info.select(".houseInfo").text();
				String houseInfos[] = houseInfo.split(" | ");
				house.setXiaoqu(houseInfos[0]);
				house.setHuxing(houseInfos[2]);
				house.setSize(houseInfos[4]);
				house.setTurned(houseInfos[6]);
				house.setPosition(info.select(".positionInfo").get(0).select("a").text());
				if (houseInfos.length > 10) {
					house.setLift(houseInfos[10]);
				}
				Date now = DateUtils.getNow();
				house.setCreateTime(now);
				house.setUpdateTime(now);
				houseInfoDao.insert(house);
			}
		} catch (IOException e) {
			LOG.error("next ", e);
		}

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
