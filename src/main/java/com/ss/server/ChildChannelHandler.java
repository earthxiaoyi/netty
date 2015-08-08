package com.ss.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("有客户端连接");
		System.out.println("ip:"+ch.localAddress().getHostName());
		System.out.println("port:"+ch.localAddress().getPort());
		
		//解码器
		//基于换行符号
		ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		//解码器 转String
		ch.pipeline().addLast(new StringDecoder());
		//编码string
		ch.pipeline().addLast(new StringEncoder());
		//管道中添加接收数据实现的方法
		ch.pipeline().addLast(new ServerHandler());
	}

}
