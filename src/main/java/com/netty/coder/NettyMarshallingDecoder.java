package com.netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * netty marshalling 编码器
 * @author jiaming.jiang
 *
 */
public class NettyMarshallingDecoder extends MarshallingDecoder{

	public NettyMarshallingDecoder(UnmarshallerProvider provider) {
		super(provider);
	}
	
	public NettyMarshallingDecoder(UnmarshallerProvider provider,
			int maxObjectSize) {
		super(provider, maxObjectSize);
	}

	public Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		return super.decode(ctx, in);
	}
	
}
