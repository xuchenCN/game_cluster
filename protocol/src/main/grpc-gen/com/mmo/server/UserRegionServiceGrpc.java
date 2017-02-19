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
public class UserRegionServiceGrpc {

  private UserRegionServiceGrpc() {}

  public static final String SERVICE_NAME = "UserRegionService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_USER_ARRIVED_REGION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserRegionService", "userArrivedRegion"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_USER_LEAVE_REGION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "UserRegionService", "userLeaveRegion"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserRegionServiceStub newStub(io.grpc.Channel channel) {
    return new UserRegionServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserRegionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserRegionServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static UserRegionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserRegionServiceFutureStub(channel);
  }

  /**
   */
  public static interface UserRegionService {

    /**
     */
    public void userArrivedRegion(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void userLeaveRegion(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractUserRegionService implements UserRegionService, io.grpc.BindableService {

    @java.lang.Override
    public void userArrivedRegion(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_ARRIVED_REGION, responseObserver);
    }

    @java.lang.Override
    public void userLeaveRegion(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_LEAVE_REGION, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return UserRegionServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface UserRegionServiceBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse userArrivedRegion(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse userLeaveRegion(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request);
  }

  /**
   */
  public static interface UserRegionServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userArrivedRegion(
        com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userLeaveRegion(
        com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request);
  }

  public static class UserRegionServiceStub extends io.grpc.stub.AbstractStub<UserRegionServiceStub>
      implements UserRegionService {
    private UserRegionServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserRegionServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRegionServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserRegionServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void userArrivedRegion(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_ARRIVED_REGION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void userLeaveRegion(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_LEAVE_REGION, getCallOptions()), request, responseObserver);
    }
  }

  public static class UserRegionServiceBlockingStub extends io.grpc.stub.AbstractStub<UserRegionServiceBlockingStub>
      implements UserRegionServiceBlockingClient {
    private UserRegionServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserRegionServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRegionServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserRegionServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse userArrivedRegion(com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_ARRIVED_REGION, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse userLeaveRegion(com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_LEAVE_REGION, getCallOptions(), request);
    }
  }

  public static class UserRegionServiceFutureStub extends io.grpc.stub.AbstractStub<UserRegionServiceFutureStub>
      implements UserRegionServiceFutureClient {
    private UserRegionServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserRegionServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserRegionServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserRegionServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userArrivedRegion(
        com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_ARRIVED_REGION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> userLeaveRegion(
        com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_LEAVE_REGION, getCallOptions()), request);
    }
  }

  private static final int METHODID_USER_ARRIVED_REGION = 0;
  private static final int METHODID_USER_LEAVE_REGION = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserRegionService serviceImpl;
    private final int methodId;

    public MethodHandlers(UserRegionService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_USER_ARRIVED_REGION:
          serviceImpl.userArrivedRegion((com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_USER_LEAVE_REGION:
          serviceImpl.userLeaveRegion((com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest) request,
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
      final UserRegionService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_USER_ARRIVED_REGION,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGameProtocol.UserArrivedRegionRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_USER_ARRIVED_REGION)))
        .addMethod(
          METHOD_USER_LEAVE_REGION,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGameProtocol.UserLeaveRegionRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_USER_LEAVE_REGION)))
        .build();
  }
}
