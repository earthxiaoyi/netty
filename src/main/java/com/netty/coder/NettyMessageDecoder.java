package com.netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.MarshallingDecoder;

import java.util.HashMap;
import java.util.Map;

import com.netty.message.Header;
import com.netty.message.Message;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder{

	NettyMarshallingDecoder nettyMarshallingDecoder;
	
	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		nettyMarshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		// TODO Auto-generated method stub
		ByteBuf frame = (ByteBuf)super.decode(ctx, in);
		if(frame==null){
			return null;
		}
		
		Message message = new Message();
		Header header = new Header();
		
		header.setSrcCode(in.readInt());
		header.setLength(in.readInt());
		header.setSessionId(in.readLong());
		header.setType(in.readByte());
		header.setPriority(in.readByte());
		int size = in.readInt();
		if(size>0){
			Map<String,Object> attch = new HashMap<String,Object>(size);
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for(int i=0;i<size;i++){
				keySize = in.readInt();
				keyArray = new byte[keySize];
				in.readBytes(keyArray);
				key = new String(keyArray,"UTF-8");
				attch.put(key, nettyMarshallingDecoder.decode(ctx, in));
			}
			keyArray = null;
			key = null;
			header.setAttachment(attch);
		}
		return message;
	}
	
	
}