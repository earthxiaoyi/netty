package com.netty.client;



import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.netty.MessageType;
import com.netty.message.Header;
import com.netty.message.Message;

public class LoginAuthReqHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//处理握手应答消息
		Message message = (Message) msg;
		//判断是否是握手应答消息
		if(message.getHeader()!=null && message.getHeader().getType()==MessageType.LOGIN_RESP.value()){
			System.out.println("client 握手消息："+message.toString());
		}
		ctx.fireChannelRead(msg);
	}
	
	private Message buildLoginReq(){
		Message message = new Message();
		Header header = new Header();
		header.setType(MessageType.LOGIN_REQ.value());
		message.setHeader(header);
		return message;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.fireExceptionCaught(cause);
	}
}
