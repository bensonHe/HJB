package org.hjb.test.lang;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 泛型擦除是指运行的时候，泛型统一处理成object,当转换回来用强制转换
 * 
 * 要把类序列号时通常要解决泛型的擦除问题，可以定义个匿名内部类解决
 * 
 * @author hejinbin 2017.05.24
 *
 */
public class Test4Generic {
	public void test() {
		System.out.println("here1");
	}

	public static void main(String[] args) {
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		Map<Integer, String> map2 = new HashMap<Integer, String>() {
		};
		Type type1 = map1.getClass().getGenericSuperclass();
		Type type2 = map2.getClass().getGenericSuperclass();
		System.out.println(type1.toString());
		System.out.println(type2.toString());
	}
}
