package org.server.gate.net;

import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_KEEPALIVE;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.server.gate.GateServerContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

public class GateNettyServer {

	private static final Log LOG = LogFactory.getLog(GateNettyServer.class);

	private int port;

	private Channel channel;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	
	private GateServerContext gateServerContext;

	public GateNettyServer(GateServerContext gateServerContext) {
		this.gateServerContext = gateServerContext;
		this.port = gateServerContext.getGateNettyPort();

		bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss", true));
		workerGroup = new NioEventLoopGroup(4, new DefaultThreadFactory("worker", true));
	}

	public void start() throws IOException {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup);
		b.channel(NioServerSocketChannel.class);

		// if (NioServerSocketChannel.class.isAssignableFrom(channelType)) {
		b.option(SO_BACKLOG, 128);
		b.childOption(SO_KEEPALIVE, true);
		// }

		b.childHandler(new ChannelInitializer<Channel>() {
			@Override
			public void initChannel(Channel ch) throws Exception {

				LOG.info("Channel init");

				ch.pipeline().addLast(new GateProtoEncoder())
						.addLast(new GateProtoDecoder()).addLast(new GateProtoEncoder())
						.addLast(new GateNettyHandler(gateServerContext));

				ch.closeFuture().addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future) {
						LOG.info("Channel closed");
					}
				});
			}
		});

		// Bind and start to accept incoming connections.
		ChannelFuture future = b.bind(port);
		try {
			future.await();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Interrupted waiting for bind");
		}
		if (!future.isSuccess()) {
			throw new IOException("Failed to bind", future.cause());
		}
		channel = future.channel();
		
		LOG.info("GateNettyServer listen on : " + port);
	}

	public void await() {
		try {
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (channel != null || channel.isOpen()) {
			channel.close();
			return;
		}

		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	public int getPort() {
		if (channel == null) {
			return -1;
		}
		SocketAddress localAddr = channel.localAddress();
		if (!(localAddr instanceof InetSocketAddress)) {
			return -1;
		}
		return ((InetSocketAddress) localAddr).getPort();
	}

}
