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
    comments = "Source: gate_server.proto")
public class GateServerServiceGrpc {

  private GateServerServiceGrpc() {}

  public static final String SERVICE_NAME = "GateServerService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.GateServerPing,
      com.mmo.server.ServerGateProtocol.GateServerPong> METHOD_RECEIVE_PING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "GateServerService", "receivePing"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.GateServerPing.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.GateServerPong.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_PLAY_BEGIN_CHANGE_MAP =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "GateServerService", "playBeginChangeMap"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_PLAYER_CHANGE_MAP_COMPLETED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "GateServerService", "PlayerChangeMapCompleted"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GateServerServiceStub newStub(io.grpc.Channel channel) {
    return new GateServerServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GateServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GateServerServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GateServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GateServerServiceFutureStub(channel);
  }

  /**
   */
  public static interface GateServerService {

    /**
     */
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver);

    /**
     */
    public void playBeginChangeMap(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void playerChangeMapCompleted(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractGateServerService implements GateServerService, io.grpc.BindableService {

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECEIVE_PING, responseObserver);
    }

    @java.lang.Override
    public void playBeginChangeMap(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PLAY_BEGIN_CHANGE_MAP, responseObserver);
    }

    @java.lang.Override
    public void playerChangeMapCompleted(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PLAYER_CHANGE_MAP_COMPLETED, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return GateServerServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface GateServerServiceBlockingClient {

    /**
     */
    public com.mmo.server.ServerGateProtocol.GateServerPong receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse playBeginChangeMap(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse playerChangeMapCompleted(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request);
  }

  /**
   */
  public static interface GateServerServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.GateServerPong> receivePing(
        com.mmo.server.ServerGateProtocol.GateServerPing request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> playBeginChangeMap(
        com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> playerChangeMapCompleted(
        com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request);
  }

  public static class GateServerServiceStub extends io.grpc.stub.AbstractStub<GateServerServiceStub>
      implements GateServerService {
    private GateServerServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GateServerServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GateServerServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GateServerServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void playBeginChangeMap(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PLAY_BEGIN_CHANGE_MAP, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void playerChangeMapCompleted(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PLAYER_CHANGE_MAP_COMPLETED, getCallOptions()), request, responseObserver);
    }
  }

  public static class GateServerServiceBlockingStub extends io.grpc.stub.AbstractStub<GateServerServiceBlockingStub>
      implements GateServerServiceBlockingClient {
    private GateServerServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GateServerServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GateServerServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GateServerServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.ServerGateProtocol.GateServerPong receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECEIVE_PING, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse playBeginChangeMap(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PLAY_BEGIN_CHANGE_MAP, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse playerChangeMapCompleted(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PLAYER_CHANGE_MAP_COMPLETED, getCallOptions(), request);
    }
  }

  public static class GateServerServiceFutureStub extends io.grpc.stub.AbstractStub<GateServerServiceFutureStub>
      implements GateServerServiceFutureClient {
    private GateServerServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GateServerServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GateServerServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GateServerServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.GateServerPong> receivePing(
        com.mmo.server.ServerGateProtocol.GateServerPing request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> playBeginChangeMap(
        com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PLAY_BEGIN_CHANGE_MAP, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> playerChangeMapCompleted(
        com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PLAYER_CHANGE_MAP_COMPLETED, getCallOptions()), request);
    }
  }

  private static final int METHODID_RECEIVE_PING = 0;
  private static final int METHODID_PLAY_BEGIN_CHANGE_MAP = 1;
  private static final int METHODID_PLAYER_CHANGE_MAP_COMPLETED = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GateServerService serviceImpl;
    private final int methodId;

    public MethodHandlers(GateServerService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_RECEIVE_PING:
          serviceImpl.receivePing((com.mmo.server.ServerGateProtocol.GateServerPing) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong>) responseObserver);
          break;
        case METHODID_PLAY_BEGIN_CHANGE_MAP:
          serviceImpl.playBeginChangeMap((com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_PLAYER_CHANGE_MAP_COMPLETED:
          serviceImpl.playerChangeMapCompleted((com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
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
      final GateServerService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_RECEIVE_PING,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.GateServerPing,
              com.mmo.server.ServerGateProtocol.GateServerPong>(
                serviceImpl, METHODID_RECEIVE_PING)))
        .addMethod(
          METHOD_PLAY_BEGIN_CHANGE_MAP,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_PLAY_BEGIN_CHANGE_MAP)))
        .addMethod(
          METHOD_PLAYER_CHANGE_MAP_COMPLETED,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_PLAYER_CHANGE_MAP_COMPLETED)))
        .build();
  }
}
