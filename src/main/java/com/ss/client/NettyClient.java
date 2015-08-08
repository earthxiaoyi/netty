package com.ss.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	public static void main(String args[]) throws Exception{
		String host = "127.0.0.1";
		int port = 8080;
		new NettyClient().connect(host,port);
	}
	
	public void connect(String host,int port) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChildChannelHandler());
			
			//发起连接
			ChannelFuture f = b.connect(host, port);
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
}
