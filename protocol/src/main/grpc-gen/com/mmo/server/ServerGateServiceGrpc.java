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
    comments = "Source: client_gate.proto")
public class ServerGateServiceGrpc {

  private ServerGateServiceGrpc() {}

  public static final String SERVICE_NAME = "ServerGateService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerClientProtocol.UserLoginRequest,
      com.mmo.server.ServerClientProtocol.UserLoginResponse> METHOD_USER_LOGIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ServerGateService", "userLogin"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.UserLoginRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.UserLoginResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerClientProtocol.UserLogoutRequest,
      com.mmo.server.ServerClientProtocol.UserLogoutResponse> METHOD_USER_LOGOUT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ServerGateService", "userLogout"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.UserLogoutRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.UserLogoutResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest,
      com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> METHOD_GET_CHARACTER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ServerGateService", "getCharacter"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerGateServiceStub newStub(io.grpc.Channel channel) {
    return new ServerGateServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerGateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ServerGateServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static ServerGateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ServerGateServiceFutureStub(channel);
  }

  /**
   */
  public static interface ServerGateService {

    /**
     */
    public void userLogin(com.mmo.server.ServerClientProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLoginResponse> responseObserver);

    /**
     */
    public void userLogout(com.mmo.server.ServerClientProtocol.UserLogoutRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLogoutResponse> responseObserver);

    /**
     */
    public void getCharacter(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractServerGateService implements ServerGateService, io.grpc.BindableService {

    @java.lang.Override
    public void userLogin(com.mmo.server.ServerClientProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_LOGIN, responseObserver);
    }

    @java.lang.Override
    public void userLogout(com.mmo.server.ServerClientProtocol.UserLogoutRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLogoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_LOGOUT, responseObserver);
    }

    @java.lang.Override
    public void getCharacter(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_CHARACTER, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return ServerGateServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface ServerGateServiceBlockingClient {

    /**
     */
    public com.mmo.server.ServerClientProtocol.UserLoginResponse userLogin(com.mmo.server.ServerClientProtocol.UserLoginRequest request);

    /**
     */
    public com.mmo.server.ServerClientProtocol.UserLogoutResponse userLogout(com.mmo.server.ServerClientProtocol.UserLogoutRequest request);

    /**
     */
    public com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse getCharacter(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request);
  }

  /**
   */
  public static interface ServerGateServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.UserLoginResponse> userLogin(
        com.mmo.server.ServerClientProtocol.UserLoginRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.UserLogoutResponse> userLogout(
        com.mmo.server.ServerClientProtocol.UserLogoutRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> getCharacter(
        com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request);
  }

  public static class ServerGateServiceStub extends io.grpc.stub.AbstractStub<ServerGateServiceStub>
      implements ServerGateService {
    private ServerGateServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerGateServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerGateServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerGateServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void userLogin(com.mmo.server.ServerClientProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_LOGIN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void userLogout(com.mmo.server.ServerClientProtocol.UserLogoutRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLogoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_LOGOUT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getCharacter(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_CHARACTER, getCallOptions()), request, responseObserver);
    }
  }

  public static class ServerGateServiceBlockingStub extends io.grpc.stub.AbstractStub<ServerGateServiceBlockingStub>
      implements ServerGateServiceBlockingClient {
    private ServerGateServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerGateServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerGateServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerGateServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.ServerClientProtocol.UserLoginResponse userLogin(com.mmo.server.ServerClientProtocol.UserLoginRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_LOGIN, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.ServerClientProtocol.UserLogoutResponse userLogout(com.mmo.server.ServerClientProtocol.UserLogoutRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_LOGOUT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse getCharacter(com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_CHARACTER, getCallOptions(), request);
    }
  }

  public static class ServerGateServiceFutureStub extends io.grpc.stub.AbstractStub<ServerGateServiceFutureStub>
      implements ServerGateServiceFutureClient {
    private ServerGateServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ServerGateServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerGateServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ServerGateServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.UserLoginResponse> userLogin(
        com.mmo.server.ServerClientProtocol.UserLoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_LOGIN, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.UserLogoutResponse> userLogout(
        com.mmo.server.ServerClientProtocol.UserLogoutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_LOGOUT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse> getCharacter(
        com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_CHARACTER, getCallOptions()), request);
    }
  }

  private static final int METHODID_USER_LOGIN = 0;
  private static final int METHODID_USER_LOGOUT = 1;
  private static final int METHODID_GET_CHARACTER = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ServerGateService serviceImpl;
    private final int methodId;

    public MethodHandlers(ServerGateService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_USER_LOGIN:
          serviceImpl.userLogin((com.mmo.server.ServerClientProtocol.UserLoginRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLoginResponse>) responseObserver);
          break;
        case METHODID_USER_LOGOUT:
          serviceImpl.userLogout((com.mmo.server.ServerClientProtocol.UserLogoutRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.UserLogoutResponse>) responseObserver);
          break;
        case METHODID_GET_CHARACTER:
          serviceImpl.getCharacter((com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse>) responseObserver);
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
      final ServerGateService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_USER_LOGIN,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerClientProtocol.UserLoginRequest,
              com.mmo.server.ServerClientProtocol.UserLoginResponse>(
                serviceImpl, METHODID_USER_LOGIN)))
        .addMethod(
          METHOD_USER_LOGOUT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerClientProtocol.UserLogoutRequest,
              com.mmo.server.ServerClientProtocol.UserLogoutResponse>(
                serviceImpl, METHODID_USER_LOGOUT)))
        .addMethod(
          METHOD_GET_CHARACTER,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerClientProtocol.GetCharacterInfoRequest,
              com.mmo.server.ServerClientProtocol.GetCharacterInfoResponse>(
                serviceImpl, METHODID_GET_CHARACTER)))
        .build();
  }
}
