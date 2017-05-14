package org.hjb.component.util;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

/**
 * 
 * @author Administrator
 *
 */
public class DecimalUtil {
	public static BigDecimal getDecimal(String str) {
		if (StringUtils.isEmpty(str)) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(str);
	}
}
