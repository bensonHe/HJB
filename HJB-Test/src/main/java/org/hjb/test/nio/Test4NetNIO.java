package org.hjb.test.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * selector 只是一个通知，具体各个部分自行处理
 * 
 * NIO , 不阻塞，当有东西来了就开始通知处理，不然能一直select到·
 * 
 * @author hejb 2017.03.06 整理
 *
 */
public class Test4NetNIO {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 阻塞为false
		serverSocketChannel.configureBlocking(false);
		// 绑定IP和端口
		serverSocketChannel.socket().bind(new InetSocketAddress("0.0.0.0", 8080));
		Selector selector = Selector.open();
		// 注册感兴趣事件到selector
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.select() != 0) {

			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {

				// 循选中事件
				SelectionKey selecttionKey = iterator.next();
				// 删除已经处理
				iterator.remove();
				if (selecttionKey.isAcceptable()) {
					// 返回注册该事件时的channel ，即SelectableChannel
					ServerSocketChannel channel = (ServerSocketChannel) selecttionKey.channel();
					// 有连接事件来了， 可以处理接收请求了,注意如果不进行accept，select.select()一直能轮询到东西
					// 接收后返回了个socketchannel，开始配置
					SocketChannel socketChannel = channel.accept();
					// 也配置成非阻塞处理
					socketChannel.configureBlocking(false);
					// 复用同一个selector上注册感兴趣的事件,并注册感兴趣的可读事件
					socketChannel.register(selector, selecttionKey.OP_READ);
				}
				// 如果来可以可读事件
				if (selecttionKey.isReadable()) {
					// 返回注册该事件时的channel ，即实现了SelectableChannel的
					SocketChannel socketChannel = (SocketChannel) selecttionKey.channel();
					// 后面就都是通过byteBuffer和channel来读操作了
					ByteBuffer byteBf = ByteBuffer.allocate(1024);
					socketChannel.read(byteBf);
					Charset charset = Charset.forName("utf-8");
					byteBf.flip();
					System.out.println("clinet :" + charset.decode(byteBf));
					// socket是双通道，故也可以直接返回东西了
					socketChannel.write(charset.encode("test only"));
				}
			}
		}
	}
	// 有个以为，NIO的链接什么时候关闭呢
}
