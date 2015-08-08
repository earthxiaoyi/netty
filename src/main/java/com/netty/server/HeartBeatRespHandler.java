package com.netty.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.netty.MessageType;
import com.netty.message.Header;
import com.netty.message.Message;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Message message = (Message)msg;
		if(message.getHeader()!=null && 
				message.getHeader().getType()==MessageType.HEARTBEAT_REQ.value()){
			
			System.out.println(" receive client heart beat message:--->"+message);
			Message heartBeat = buildHeartBeat();
			ctx.writeAndFlush(heartBeat);
			
		}else{
			
			ctx.fireChannelRead(msg);
			
		}
	}
	
	
	private Message buildHeartBeat(){
		Message message = new Message();
		Header h = new Header();
		h.setType(MessageType.HEARTBEAT_RESP.value());
		message.setHeader(h);
		return message;
	}
}
