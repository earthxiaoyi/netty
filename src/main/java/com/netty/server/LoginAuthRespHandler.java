package com.netty.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import com.netty.MessageType;
import com.netty.message.Header;
import com.netty.message.Message;

public class LoginAuthRespHandler extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

	private Message buildLoginResponse(byte result) {
		Message message = new Message();
		Header h = new Header();
		h.setType(MessageType.LOGIN_RESP.value());
		message.setBody(result);
		message.setHeader(h);
		return message;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Message message = (Message) msg;
		if (message.getHeader() != null
				&& message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
			System.out.println("login is ok!");
			System.out.println("server 握手消息："+message.toString());
		}
		ctx.fireChannelRead(message);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}
