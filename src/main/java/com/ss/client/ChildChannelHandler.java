package com.ss.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;

public class ChildChannelHandler extends ChannelInitializer{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		System.out.println("客户端连接到服务端");
	}
	
}
