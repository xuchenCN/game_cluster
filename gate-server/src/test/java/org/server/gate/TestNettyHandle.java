package org.server.gate;

import org.server.gate.net.FlexiblePBDecoder;
import org.server.gate.net.GateClientMessage;

import com.mmo.server.MessagesLocation.MessageRegistry;
import com.mmo.server.ServerClientProtocol.UserLoginRequest;
import com.mmo.server.ServerClientProtocol.UserLoginResponse;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestNettyHandle extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		UserLoginRequest req = UserLoginRequest.newBuilder().setUname("tester1").setUpwd("tester1").build();

		GateClientMessage msg = new GateClientMessage(MessageRegistry.USERLOGINREQUEST, Unpooled.wrappedBuffer(req.toByteArray()));

		ctx.writeAndFlush(msg);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		GateClientMessage message = (GateClientMessage)msg;
		System.out.println(message.messageId);
		System.out.println(message.body);
		
		switch (message.messageId.getNumber()) {
		case MessageRegistry.USERLOGINRESPONSE_VALUE:
			System.out.println(FlexiblePBDecoder.decode(UserLoginResponse.getDefaultInstance(),null,message.body));
			break;
		}
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

}
