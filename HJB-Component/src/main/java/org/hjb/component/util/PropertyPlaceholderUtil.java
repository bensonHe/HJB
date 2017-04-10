package org.hjb.component.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 用于读取Property文件,用于扩充spring读取资源文件类
 * 
 * @author hejb 2015.07.27
 */
public class PropertyPlaceholderUtil extends PropertyPlaceholderConfigurer {
	private static Logger logger = LoggerFactory.getLogger(PropertyPlaceholderUtil.class);

	private Resource[] locations;

	// 用current，主要防止map的扩容并发死链并发问题
	private static Map<String, String> ctxPropertiesMap = new java.util.concurrent.ConcurrentHashMap<String, String>();

	@Override
	public void setLocations(Resource[] locations) {

		this.locations = locations;
	}

	@Override
	protected void loadProperties(Properties props) throws IOException {

		if (this.locations != null) {
			for (Resource location : this.locations) {
				try {
					logger.info("location : {}", location.getURL());
					PropertiesLoaderUtils.fillProperties(props, new EncodedResource(location));
					Iterator<Object> iterator = props.keySet().iterator();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						ctxPropertiesMap.put(key, props.getProperty(key));
					}
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
	}

	public static String getPropertyValue(String key) {

		return ctxPropertiesMap.get(key);
	}

}
