package org.server.gate.net;

import static io.netty.channel.ChannelOption.SO_BACKLOG;
import static io.netty.channel.ChannelOption.SO_KEEPALIVE;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mmo.server.ServerClientProtocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultThreadFactory;

public class GateNettyServer {

	private static final Log LOG = LogFactory.getLog(GateNettyServer.class);

	private int port;

	private Channel channel;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	public GateNettyServer(int port) {
		this.port = port;

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

				ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
						.addLast(new ProtobufDecoder(ServerClientProtocol.UserLoginRequest.getDefaultInstance()))
						.addLast(new ProtobufVarint32LengthFieldPrepender()).addLast(new ProtobufEncoder())
						.addLast(new GateNettyHandler());

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
	}

	public void await() {
		try {
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
