package com.homework.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter{

	private byte[] firstMessage;
	
	public ClientHandler(){
		firstMessage = ("hello servers!"+System.getProperty("line.separator")).getBytes();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body = (String)msg;
		System.out.println("收到的消息为："+body);
	}
	

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for (int i = 0; i < 100; i++) {
		    message = Unpooled.buffer(firstMessage.length);
		    message.writeBytes(firstMessage);
		    ctx.writeAndFlush(message);
		}
	}
}
