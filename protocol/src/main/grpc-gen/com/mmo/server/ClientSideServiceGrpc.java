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
public class ClientSideServiceGrpc {

  private ClientSideServiceGrpc() {}

  public static final String SERVICE_NAME = "ClientSideService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerClientProtocol.CharacterMove,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_MOVE_TO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "ClientSideService", "moveTo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerClientProtocol.CharacterMove.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientSideServiceStub newStub(io.grpc.Channel channel) {
    return new ClientSideServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientSideServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ClientSideServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static ClientSideServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ClientSideServiceFutureStub(channel);
  }

  /**
   */
  public static interface ClientSideService {

    /**
     */
    public void moveTo(com.mmo.server.ServerClientProtocol.CharacterMove request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractClientSideService implements ClientSideService, io.grpc.BindableService {

    @java.lang.Override
    public void moveTo(com.mmo.server.ServerClientProtocol.CharacterMove request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MOVE_TO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return ClientSideServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface ClientSideServiceBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse moveTo(com.mmo.server.ServerClientProtocol.CharacterMove request);
  }

  /**
   */
  public static interface ClientSideServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveTo(
        com.mmo.server.ServerClientProtocol.CharacterMove request);
  }

  public static class ClientSideServiceStub extends io.grpc.stub.AbstractStub<ClientSideServiceStub>
      implements ClientSideService {
    private ClientSideServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientSideServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientSideServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientSideServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void moveTo(com.mmo.server.ServerClientProtocol.CharacterMove request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MOVE_TO, getCallOptions()), request, responseObserver);
    }
  }

  public static class ClientSideServiceBlockingStub extends io.grpc.stub.AbstractStub<ClientSideServiceBlockingStub>
      implements ClientSideServiceBlockingClient {
    private ClientSideServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientSideServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientSideServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientSideServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse moveTo(com.mmo.server.ServerClientProtocol.CharacterMove request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MOVE_TO, getCallOptions(), request);
    }
  }

  public static class ClientSideServiceFutureStub extends io.grpc.stub.AbstractStub<ClientSideServiceFutureStub>
      implements ClientSideServiceFutureClient {
    private ClientSideServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClientSideServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientSideServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClientSideServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveTo(
        com.mmo.server.ServerClientProtocol.CharacterMove request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MOVE_TO, getCallOptions()), request);
    }
  }

  private static final int METHODID_MOVE_TO = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientSideService serviceImpl;
    private final int methodId;

    public MethodHandlers(ClientSideService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MOVE_TO:
          serviceImpl.moveTo((com.mmo.server.ServerClientProtocol.CharacterMove) request,
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
      final ClientSideService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_MOVE_TO,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerClientProtocol.CharacterMove,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_MOVE_TO)))
        .build();
  }
}
