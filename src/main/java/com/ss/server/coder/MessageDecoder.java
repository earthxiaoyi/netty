package com.ss.server.coder;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

	private Charset charset;
	
	public MessageDecoder (){
		this(Charset.defaultCharset());
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
			List<Object> out) throws Exception {
		//跳过4个字节
		msg = msg.skipBytes(4);
		//剔除最后一个字符串 03
		msg = msg.copy(0, msg.readableBytes()-1);
		//加进集合中
		out.add(msg.toString(charset));
	}
	
	public MessageDecoder(Charset charset){
		if(charset == null){
			throw new NullPointerException("charset is null");
		}
		this.charset = charset;
	}
}
