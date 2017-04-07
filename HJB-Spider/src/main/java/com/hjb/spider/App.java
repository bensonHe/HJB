package com.hjb.spider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
	public static final Logger LOG = Logger.getLogger(App.class);

	public static void catchLianjia(int startPage, int endPage) throws IOException {
		for (int i = 1; i <= endPage; i++) {
			try {
				Document doc = Jsoup.connect("http://gz.lianjia.com/ershoufang/pg" + i + "co32/").timeout(3000)
						.userAgent(
								"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50")
						.ignoreContentType(true).get();
				Elements infos = doc.getElementsByClass("sellListContent").first().select(".clear");
				for (Element info : infos) {
					String image = info.select(".lj-lazy").attr("data-original");
					info = doc.getElementsByClass("info clear").first();
					String href = info.select(".title").select("a").attr("href");
					String title = info.select(".title").select("a").html();
					String totalPrice = info.select(".totalPrice").select("span").html();
					String unitPrice = info.select(".unitPrice").attr("data-price");
					String houseId = info.select(".unitPrice").attr("data-hid");
					String houseInfo = info.select(".houseInfo").text();
					String houseInfos[] = houseInfo.split(" | ");
					String xiaoqu = houseInfos[0];
					String huxing = houseInfos[2];
					String size = houseInfos[4];
					String turned = houseInfos[4];
					String remark = houseInfos[8];
					String lift = "";
					String position = info.select(".positionInfo").get(0).select("a").text();
					if (houseInfos.length > 10) {
						lift = houseInfos[10];
					}
					System.out.println("房子ID：" + houseId);
					System.out.println("房子：" + title);
					System.out.println("总价:" + totalPrice);
					System.out.println("单价:" + unitPrice);
					System.out.println("地址:" + position);
					System.out.println("小区:" + xiaoqu);
					System.out.println("户型:" + huxing);
					System.out.println("大小:" + size);
					System.out.println("朝向:" + turned);
					System.out.println("说明:" + remark);
					System.out.println("电梯:" + lift);
					System.out.println("图片:" + image);
					System.out.println("链接地址:" + href);
					System.out.println("--------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		catchLianjia(1, 1);
	}
}
