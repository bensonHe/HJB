package org.hjb.test.lang;

/**
 * threadLoal, 在thread对象里保存一个map, 每次get时先获取当前线程的map，
 * 在把当前的threadLocal对象作为key从map里获取值
 * 
 * @author hejinbin 2017.05.24
 *
 */
public class Test4ThreadLocal {

	static ThreadLocal<String> threadLocal1 = new ThreadLocal<String>();
	static ThreadLocal<String> threadLocal2 = new ThreadLocal<String>();

	public static void main(String[] args) {
		threadLocal1.set("threadLocal1");
		System.out.println(threadLocal1.get());
		threadLocal2.set("threadLocal2");
		System.out.println(threadLocal2.get());
	}
}
