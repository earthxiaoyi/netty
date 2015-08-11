package com.ss.server.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<CharSequence> {
	
	private Charset charset;
	
	//添加头与尾
  	private final byte[] headByte = {0,0,0,0},bottomByte = {3};
	
	public MessageEncoder(){
		this(Charset.defaultCharset());
	}
	
	public MessageEncoder(Charset charset){
		if(charset==null){
			throw new  NullPointerException("charset is null");
		}
		this.charset = charset;
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, CharSequence msg,
			List<Object> out) throws Exception {
		if(msg.length()==0){
			return;
		}
		
		out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(new String(headByte)+msg+new String(bottomByte)), charset));
	}

}
