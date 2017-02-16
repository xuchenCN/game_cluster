package com.mmo.server;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.14.1)",
    comments = "Source: game_server.proto")
public class GameServerServiceGrpc {

  private GameServerServiceGrpc() {}

  public static final String SERVICE_NAME = "GameServerService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGameProtocol.GameServerPing,
      com.mmo.server.ServerGameProtocol.GameServerPong> METHOD_RECEIVE_PING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "GameServerService", "receivePing"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGameProtocol.GameServerPing.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGameProtocol.GameServerPong.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameServerServiceStub newStub(io.grpc.Channel channel) {
    return new GameServerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GameServerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GameServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GameServerServiceFutureStub(channel);
  }

  /**
   */
  public static interface GameServerService {

    /**
     */
    public void receivePing(com.mmo.server.ServerGameProtocol.GameServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGameProtocol.GameServerPong> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractGameServerService implements GameServerService, io.grpc.BindableService {

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGameProtocol.GameServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGameProtocol.GameServerPong> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECEIVE_PING, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return GameServerServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface GameServerServiceBlockingClient {

    /**
     */
    public com.mmo.server.ServerGameProtocol.GameServerPong receivePing(com.mmo.server.ServerGameProtocol.GameServerPing request);
  }

  /**
   */
  public static interface GameServerServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGameProtocol.GameServerPong> receivePing(
        com.mmo.server.ServerGameProtocol.GameServerPing request);
  }

  public static class GameServerServiceStub extends io.grpc.stub.AbstractStub<GameServerServiceStub>
      implements GameServerService {
    private GameServerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServerServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGameProtocol.GameServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGameProtocol.GameServerPong> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request, responseObserver);
    }
  }

  public static class GameServerServiceBlockingStub extends io.grpc.stub.AbstractStub<GameServerServiceBlockingStub>
      implements GameServerServiceBlockingClient {
    private GameServerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServerServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.ServerGameProtocol.GameServerPong receivePing(com.mmo.server.ServerGameProtocol.GameServerPing request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECEIVE_PING, getCallOptions(), request);
    }
  }

  public static class GameServerServiceFutureStub extends io.grpc.stub.AbstractStub<GameServerServiceFutureStub>
      implements GameServerServiceFutureClient {
    private GameServerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServerServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGameProtocol.GameServerPong> receivePing(
        com.mmo.server.ServerGameProtocol.GameServerPing request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_PING = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameServerService serviceImpl;
    private final int methodId;

    public MethodHandlers(GameServerService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_PING:
          serviceImpl.receivePing((com.mmo.server.ServerGameProtocol.GameServerPing) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGameProtocol.GameServerPong>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final GameServerService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_RECEIVE_PING,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGameProtocol.GameServerPing,
              com.mmo.server.ServerGameProtocol.GameServerPong>(
                serviceImpl, METHODID_RECEIVE_PING)))
        .build();
  }
}
