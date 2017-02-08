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
    comments = "Source: world_server.proto")
public class UserWorldServiceGrpc {

  private UserWorldServiceGrpc() {}

  public static final String SERVICE_NAME = "UserWorldService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_USER_ARRIVED_WORLD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserWorldService", "userArrivedWorld"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserWorldServiceStub newStub(io.grpc.Channel channel) {
    return new UserWorldServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserWorldServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserWorldServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static UserWorldServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserWorldServiceFutureStub(channel);
  }

  /**
   */
  public static interface UserWorldService {

    /**
     */
    public void userArrivedWorld(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractUserWorldService implements UserWorldService, io.grpc.BindableService {

    @java.lang.Override
    public void userArrivedWorld(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_ARRIVED_WORLD, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return UserWorldServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface UserWorldServiceBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse userArrivedWorld(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request);
  }

  /**
   */
  public static interface UserWorldServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userArrivedWorld(
        com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request);
  }

  public static class UserWorldServiceStub extends io.grpc.stub.AbstractStub<UserWorldServiceStub>
      implements UserWorldService {
    private UserWorldServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserWorldServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserWorldServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserWorldServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void userArrivedWorld(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_ARRIVED_WORLD, getCallOptions()), request, responseObserver);
    }
  }

  public static class UserWorldServiceBlockingStub extends io.grpc.stub.AbstractStub<UserWorldServiceBlockingStub>
      implements UserWorldServiceBlockingClient {
    private UserWorldServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserWorldServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserWorldServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserWorldServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse userArrivedWorld(com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_ARRIVED_WORLD, getCallOptions(), request);
    }
  }

  public static class UserWorldServiceFutureStub extends io.grpc.stub.AbstractStub<UserWorldServiceFutureStub>
      implements UserWorldServiceFutureClient {
    private UserWorldServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserWorldServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserWorldServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserWorldServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userArrivedWorld(
        com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_ARRIVED_WORLD, getCallOptions()), request);
    }
  }

  private static final int METHODID_USER_ARRIVED_WORLD = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserWorldService serviceImpl;
    private final int methodId;

    public MethodHandlers(UserWorldService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_USER_ARRIVED_WORLD:
          serviceImpl.userArrivedWorld((com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest) request,
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
      final UserWorldService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_USER_ARRIVED_WORLD,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerWorldProtocol.UserArrivedWorldRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_USER_ARRIVED_WORLD)))
        .build();
  }
}
