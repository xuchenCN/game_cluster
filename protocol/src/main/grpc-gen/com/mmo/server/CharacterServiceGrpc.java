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
public class CharacterServiceGrpc {

  private CharacterServiceGrpc() {}

  public static final String SERVICE_NAME = "CharacterService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGameProtocol.CharacterMoveReq,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_MOVE_TO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "CharacterService", "moveTo"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGameProtocol.CharacterMoveReq.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CharacterServiceStub newStub(io.grpc.Channel channel) {
    return new CharacterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CharacterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CharacterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static CharacterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CharacterServiceFutureStub(channel);
  }

  /**
   */
  public static interface CharacterService {

    /**
     */
    public void moveTo(com.mmo.server.ServerGameProtocol.CharacterMoveReq request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractCharacterService implements CharacterService, io.grpc.BindableService {

    @java.lang.Override
    public void moveTo(com.mmo.server.ServerGameProtocol.CharacterMoveReq request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MOVE_TO, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return CharacterServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface CharacterServiceBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse moveTo(com.mmo.server.ServerGameProtocol.CharacterMoveReq request);
  }

  /**
   */
  public static interface CharacterServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveTo(
        com.mmo.server.ServerGameProtocol.CharacterMoveReq request);
  }

  public static class CharacterServiceStub extends io.grpc.stub.AbstractStub<CharacterServiceStub>
      implements CharacterService {
    private CharacterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CharacterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CharacterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CharacterServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void moveTo(com.mmo.server.ServerGameProtocol.CharacterMoveReq request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MOVE_TO, getCallOptions()), request, responseObserver);
    }
  }

  public static class CharacterServiceBlockingStub extends io.grpc.stub.AbstractStub<CharacterServiceBlockingStub>
      implements CharacterServiceBlockingClient {
    private CharacterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CharacterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CharacterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CharacterServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse moveTo(com.mmo.server.ServerGameProtocol.CharacterMoveReq request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MOVE_TO, getCallOptions(), request);
    }
  }

  public static class CharacterServiceFutureStub extends io.grpc.stub.AbstractStub<CharacterServiceFutureStub>
      implements CharacterServiceFutureClient {
    private CharacterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CharacterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CharacterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CharacterServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveTo(
        com.mmo.server.ServerGameProtocol.CharacterMoveReq request) {
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
    private final CharacterService serviceImpl;
    private final int methodId;

    public MethodHandlers(CharacterService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MOVE_TO:
          serviceImpl.moveTo((com.mmo.server.ServerGameProtocol.CharacterMoveReq) request,
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
      final CharacterService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_MOVE_TO,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGameProtocol.CharacterMoveReq,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_MOVE_TO)))
        .build();
  }
}
