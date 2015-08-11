package com.ss.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

	
	/**
	 * 抓住异常，当发生异常的时候，做一些相应的处理：关闭连接，打印日志；
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
		System.out.println("发生异常；"+cause.getMessage());
	}

	/**
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 * 
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().localAddress().toString()+" channelActive");
	}

	/**
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。
	 * 也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().localAddress().toString()+" channelInactive");
	}

	/**
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
	 * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//使用stringdecoder后，不需要手工转码了
		/*
		ByteBuf buf = (ByteBuf)msg;
		byte[] msgByte = new byte[buf.readableBytes()];
		buf.readBytes(msgByte);
		*/
		System.out.println("消息为："+msg.toString());
		
		ctx.writeAndFlush("your is connected server~\n\r");
	}

	/**
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
}
