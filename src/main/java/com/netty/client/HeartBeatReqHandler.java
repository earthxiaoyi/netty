package com.netty.client;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

import com.netty.MessageType;
import com.netty.message.Header;
import com.netty.message.Message;

public class HeartBeatReqHandler extends ChannelHandlerAdapter {
	//netty
	private volatile ScheduledFuture<?> heartBeat;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Message message = (Message)msg;
		//握手成功主动发送心跳
		if(message.getHeader()!=null && message.getHeader().getType()
				==MessageType.LOGIN_RESP.value()){
			
			heartBeat = ctx.executor().scheduleAtFixedRate(
					new HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
			
		}else if(message.getHeader()!=null && 
				message.getHeader().getType()==MessageType.HEARTBEAT_RESP.value()){
			
			System.out.println("Client receive server heart beat message:---->"+message);
			
		}else{
			ctx.fireChannelRead(message);
		}
	}
	
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		
		if(heartBeat!=null){
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);
		
	}



	private class HeartBeatTask implements Runnable{
		
		private final ChannelHandlerContext ctx;
		
		public HeartBeatTask(final ChannelHandlerContext ctx){
			this.ctx = ctx;
		}
		
		public void run() {
			Message message = buildHeatBeat();
			System.out.println("client 心跳："+message);
			ctx.writeAndFlush(message);
		}
		
		private Message buildHeatBeat(){
			Message message = new Message();
			Header h = new Header();
			h.setType(MessageType.HEARTBEAT_REQ.value());
			message.setHeader(h);
			return message;
		}
		
	}
	
}
