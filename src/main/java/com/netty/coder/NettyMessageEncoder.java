package com.netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

import java.util.List;
import java.util.Map;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import com.netty.message.Message;

/**
 * netty消息解码器
 * @author jiaming.jiang
 *
 */
public final class NettyMessageEncoder extends MessageToMessageEncoder<Message>{

	NettyMarshallingEncoder marshallingEncoder = null;
	
	
	public NettyMessageEncoder(){
		this.marshallingEncoder = MarshallingCodeCFactory.buildMarshallingEncoder();
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg,
			List<Object> out) throws Exception {
		if(msg==null || msg.getHeader()==null)
			throw new Exception("The encode message is null");
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt((msg.getHeader().getSrcCode()));
		sendBuf.writeInt((msg.getHeader().getLength()));
		sendBuf.writeLong((msg.getHeader().getSessionId()));
		sendBuf.writeByte((msg.getHeader().getType()));
		sendBuf.writeByte((msg.getHeader().getPriority()));
		sendBuf.writeInt((msg.getHeader().getAttachment().size()));
		
		//处理附件类型
		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for(Map.Entry<String, Object> param:msg.getHeader().getAttachment().entrySet()){
			key = param.getKey();
			keyArray = key.getBytes();
			sendBuf.writeInt(keyArray.length);
			sendBuf.writeBytes(keyArray);
			value = param.getValue();
			marshallingEncoder.encode(ctx, value, sendBuf);
		}
		
		key = null;
		keyArray= null;
		value = null;
		if(msg.getBody()!=null){
			marshallingEncoder.encode(ctx, msg.getBody(), sendBuf);
		}else{
			sendBuf.writeInt(0);
			sendBuf.setInt(4, sendBuf.readableBytes());
		}
	}
}
