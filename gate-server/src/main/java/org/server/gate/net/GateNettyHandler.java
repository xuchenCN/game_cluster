package org.server.gate.net;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import com.mmo.server.ServerClientProtocol;
import com.mmo.server.ServerClientProtocol.LoginCode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GateNettyHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive " + ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive" + ctx.channel().remoteAddress());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("userEventTriggered " + evt);
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ServerClientProtocol.UserLoginRequest req = (ServerClientProtocol.UserLoginRequest) msg;

		System.out.println(req);

		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		forkJoinPool.submit(() -> {
			IntStream.range(0, 10).parallel().forEach(n -> {
				ServerClientProtocol.UserLoginResponse resp = ServerClientProtocol.UserLoginResponse.newBuilder()
						.setCode(LoginCode.SUC).setTicket(Thread.currentThread().getName() + " " + n).build();
				ctx.writeAndFlush(resp);

			});

		});

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
