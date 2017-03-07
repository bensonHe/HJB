package org.hjb.component;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 本地缓存
 * 
 * hejinbin 2017.02.24
 */
public class LocalMemory {

	// 数据
	static class CacheData {

		// 过期时间
		private Long invalidTime;

		private Object data;

		public Long getInvalidTime() {
			return invalidTime;
		}

		public void setInvalidTime(Long invalidTime) {
			this.invalidTime = invalidTime;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
	}

	private static Logger logger = LogManager.getLogger(LocalMemory.class);

	// 存储本地缓存数据.用软引用避免OutOfMemoryError
	static Map<String, SoftReference<CacheData>> localData = new ConcurrentHashMap<String, SoftReference<CacheData>>();

	public static final int MAX_SIZE = 10000;

	public static final int WARN_VALUE = 8000;

	/**
	 * @param key
	 *            缓存KEY
	 * @param value
	 *            缓存数据
	 * @param timeOut
	 *            超时时间，单位秒
	 */
	public static void put(String key, Object value, Long timeOut) {

		if (localData.size() >= WARN_VALUE) {
			logger.warn("注意:本地缓存已经达到临界值,size:" + localData.size());
		}
		if (localData.size() > MAX_SIZE) {
			logger.error("超出最大值:" + localData.size());
			return;
		}
		CacheData cacheData = new CacheData();

		long now = System.currentTimeMillis();
		long invalidTime = now + (timeOut * 1000);
		cacheData.setData(value);
		cacheData.setInvalidTime(invalidTime);
		SoftReference<CacheData> refCacheData = new SoftReference<CacheData>(cacheData);
		localData.put(key, refCacheData);

	}

	public static final Object get(String key) {
		SoftReference<CacheData> referenceData = localData.get(key);
		if (referenceData == null) {
			logger.debug("未找到数据，key => {}", key);
		}
		CacheData cacheData = localData.get(key).get();
		if (cacheData == null) {
			logger.debug("未找到数据，key => {}", key);
		}
		Long invalidTime = cacheData.getInvalidTime();
		if (invalidTime == null) {
			return null;
		}
		long now = System.currentTimeMillis();
		if (now > invalidTime) {
			// 清除缓存
			localData.remove(key);
			return null;
		}
		return cacheData.getData();
	}

	public static void put(String key, Object value, long time, TimeUnit unit) {
		put(key, value, unit.toSeconds(time));
	}

	public static void main(String[] args) throws InterruptedException {
		String key = "test";
		Object value = "hello world";
		LocalMemory.put("test", value, 1l);
		System.out.println(LocalMemory.get(key));
		Thread.sleep(2000);
		System.out.println(LocalMemory.get(key));
	}

}
