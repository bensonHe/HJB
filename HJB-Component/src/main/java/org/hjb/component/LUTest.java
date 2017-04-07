package org.hjb.component;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LUTest {
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://gz.lianjia.com/ershoufang/pg3").get();
		Elements infos = doc.getElementsByClass("info clear");
		for (Element info : infos) {
			String href = info.select(".title").select("a").attr("href");
			String title = info.select(".title").select("a").html();
			String price = info.select(".totalPrice").select("span").html();
			String xiaoqu = info.select(".houseInfo").select("a").html();
			System.out.println("房子：" + title);
			System.out.println("价格:" + price + "万");
			System.out.println("所属小区:" + xiaoqu);
			System.out.println("链接地址:" + href);
			System.out.println("--------------------------------------------");
		}

	}
}
