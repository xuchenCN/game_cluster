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
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.UserLoginRequest,
      com.mmo.server.ServerGateProtocol.UserLoginResponse> METHOD_USER_LOGIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ServerGateService", "UserLogin"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.UserLoginRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.UserLoginResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.MasterServerId,
      com.mmo.server.ServerGateProtocol.MasterServer> METHOD_LIST_MASTERS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "ServerGateService", "ListMasters"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.MasterServerId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.MasterServer.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.MasterServerId,
      com.mmo.server.ServerGateProtocol.MasterServer> METHOD_STATUS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          generateFullMethodName(
              "ServerGateService", "status"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.MasterServerId.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.MasterServer.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.ChatMsg,
      com.mmo.server.ServerGateProtocol.ChatMsg> METHOD_CHAT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "ServerGateService", "chat"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.ChatMsg.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.ChatMsg.getDefaultInstance()));

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
    public void userLogin(com.mmo.server.ServerGateProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.UserLoginResponse> responseObserver);

    /**
     * <pre>
     *server-side
     * </pre>
     */
    public void listMasters(com.mmo.server.ServerGateProtocol.MasterServerId request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver);

    /**
     * <pre>
     *client-side
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServerId> status(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver);

    /**
     * <pre>
     *Bidirectional
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> chat(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractServerGateService implements ServerGateService, io.grpc.BindableService {

    @java.lang.Override
    public void userLogin(com.mmo.server.ServerGateProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.UserLoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_USER_LOGIN, responseObserver);
    }

    @java.lang.Override
    public void listMasters(com.mmo.server.ServerGateProtocol.MasterServerId request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST_MASTERS, responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServerId> status(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_STATUS, responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> chat(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_CHAT, responseObserver);
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
    public com.mmo.server.ServerGateProtocol.UserLoginResponse userLogin(com.mmo.server.ServerGateProtocol.UserLoginRequest request);

    /**
     * <pre>
     *server-side
     * </pre>
     */
    public java.util.Iterator<com.mmo.server.ServerGateProtocol.MasterServer> listMasters(
        com.mmo.server.ServerGateProtocol.MasterServerId request);
  }

  /**
   */
  public static interface ServerGateServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.UserLoginResponse> userLogin(
        com.mmo.server.ServerGateProtocol.UserLoginRequest request);
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
    public void userLogin(com.mmo.server.ServerGateProtocol.UserLoginRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.UserLoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_USER_LOGIN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void listMasters(com.mmo.server.ServerGateProtocol.MasterServerId request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_LIST_MASTERS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServerId> status(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_STATUS, getCallOptions()), responseObserver);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> chat(
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_CHAT, getCallOptions()), responseObserver);
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
    public com.mmo.server.ServerGateProtocol.UserLoginResponse userLogin(com.mmo.server.ServerGateProtocol.UserLoginRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_USER_LOGIN, getCallOptions(), request);
    }

    @java.lang.Override
    public java.util.Iterator<com.mmo.server.ServerGateProtocol.MasterServer> listMasters(
        com.mmo.server.ServerGateProtocol.MasterServerId request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_LIST_MASTERS, getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.UserLoginResponse> userLogin(
        com.mmo.server.ServerGateProtocol.UserLoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_USER_LOGIN, getCallOptions()), request);
    }
  }

  private static final int METHODID_USER_LOGIN = 0;
  private static final int METHODID_LIST_MASTERS = 1;
  private static final int METHODID_STATUS = 2;
  private static final int METHODID_CHAT = 3;

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
          serviceImpl.userLogin((com.mmo.server.ServerGateProtocol.UserLoginRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.UserLoginResponse>) responseObserver);
          break;
        case METHODID_LIST_MASTERS:
          serviceImpl.listMasters((com.mmo.server.ServerGateProtocol.MasterServerId) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer>) responseObserver);
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
        case METHODID_STATUS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.status(
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.MasterServer>) responseObserver);
        case METHODID_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chat(
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.ChatMsg>) responseObserver);
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
              com.mmo.server.ServerGateProtocol.UserLoginRequest,
              com.mmo.server.ServerGateProtocol.UserLoginResponse>(
                serviceImpl, METHODID_USER_LOGIN)))
        .addMethod(
          METHOD_LIST_MASTERS,
          asyncServerStreamingCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.MasterServerId,
              com.mmo.server.ServerGateProtocol.MasterServer>(
                serviceImpl, METHODID_LIST_MASTERS)))
        .addMethod(
          METHOD_STATUS,
          asyncClientStreamingCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.MasterServerId,
              com.mmo.server.ServerGateProtocol.MasterServer>(
                serviceImpl, METHODID_STATUS)))
        .addMethod(
          METHOD_CHAT,
          asyncBidiStreamingCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.ChatMsg,
              com.mmo.server.ServerGateProtocol.ChatMsg>(
                serviceImpl, METHODID_CHAT)))
        .build();
  }
}
