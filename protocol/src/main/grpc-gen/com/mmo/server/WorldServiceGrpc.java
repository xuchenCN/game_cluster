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
public class WorldServiceGrpc {

  private WorldServiceGrpc() {}

  public static final String SERVICE_NAME = "WorldService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerWorldProtocol.GateRegisterRequest,
      com.mmo.server.ServerWorldProtocol.GateRegisterResponse> METHOD_REGISTER_GATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "WorldService", "registerGate"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.GateRegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.GateRegisterResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerWorldProtocol.RegionRegisterRequest,
      com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> METHOD_REGISTER_REGION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "WorldService", "registerRegion"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.RegionRegisterResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_REGISTER_CHARACTER_SERVER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "WorldService", "registerCharacterServer"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WorldServiceStub newStub(io.grpc.Channel channel) {
    return new WorldServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WorldServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WorldServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static WorldServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WorldServiceFutureStub(channel);
  }

  /**
   */
  public static interface WorldService {

    /**
     */
    public void registerGate(com.mmo.server.ServerWorldProtocol.GateRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.GateRegisterResponse> responseObserver);

    /**
     */
    public void registerRegion(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> responseObserver);

    /**
     */
    public void registerCharacterServer(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractWorldService implements WorldService, io.grpc.BindableService {

    @java.lang.Override
    public void registerGate(com.mmo.server.ServerWorldProtocol.GateRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.GateRegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_GATE, responseObserver);
    }

    @java.lang.Override
    public void registerRegion(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_REGION, responseObserver);
    }

    @java.lang.Override
    public void registerCharacterServer(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REGISTER_CHARACTER_SERVER, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return WorldServiceGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface WorldServiceBlockingClient {

    /**
     */
    public com.mmo.server.ServerWorldProtocol.GateRegisterResponse registerGate(com.mmo.server.ServerWorldProtocol.GateRegisterRequest request);

    /**
     */
    public com.mmo.server.ServerWorldProtocol.RegionRegisterResponse registerRegion(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse registerCharacterServer(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request);
  }

  /**
   */
  public static interface WorldServiceFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerWorldProtocol.GateRegisterResponse> registerGate(
        com.mmo.server.ServerWorldProtocol.GateRegisterRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> registerRegion(
        com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> registerCharacterServer(
        com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request);
  }

  public static class WorldServiceStub extends io.grpc.stub.AbstractStub<WorldServiceStub>
      implements WorldService {
    private WorldServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WorldServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WorldServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WorldServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void registerGate(com.mmo.server.ServerWorldProtocol.GateRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.GateRegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_GATE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void registerRegion(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_REGION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void registerCharacterServer(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REGISTER_CHARACTER_SERVER, getCallOptions()), request, responseObserver);
    }
  }

  public static class WorldServiceBlockingStub extends io.grpc.stub.AbstractStub<WorldServiceBlockingStub>
      implements WorldServiceBlockingClient {
    private WorldServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WorldServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WorldServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WorldServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.ServerWorldProtocol.GateRegisterResponse registerGate(com.mmo.server.ServerWorldProtocol.GateRegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_GATE, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.ServerWorldProtocol.RegionRegisterResponse registerRegion(com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_REGION, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse registerCharacterServer(com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REGISTER_CHARACTER_SERVER, getCallOptions(), request);
    }
  }

  public static class WorldServiceFutureStub extends io.grpc.stub.AbstractStub<WorldServiceFutureStub>
      implements WorldServiceFutureClient {
    private WorldServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WorldServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WorldServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WorldServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerWorldProtocol.GateRegisterResponse> registerGate(
        com.mmo.server.ServerWorldProtocol.GateRegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_GATE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse> registerRegion(
        com.mmo.server.ServerWorldProtocol.RegionRegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_REGION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> registerCharacterServer(
        com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REGISTER_CHARACTER_SERVER, getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_GATE = 0;
  private static final int METHODID_REGISTER_REGION = 1;
  private static final int METHODID_REGISTER_CHARACTER_SERVER = 2;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WorldService serviceImpl;
    private final int methodId;

    public MethodHandlers(WorldService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_GATE:
          serviceImpl.registerGate((com.mmo.server.ServerWorldProtocol.GateRegisterRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.GateRegisterResponse>) responseObserver);
          break;
        case METHODID_REGISTER_REGION:
          serviceImpl.registerRegion((com.mmo.server.ServerWorldProtocol.RegionRegisterRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerWorldProtocol.RegionRegisterResponse>) responseObserver);
          break;
        case METHODID_REGISTER_CHARACTER_SERVER:
          serviceImpl.registerCharacterServer((com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest) request,
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
      final WorldService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_REGISTER_GATE,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerWorldProtocol.GateRegisterRequest,
              com.mmo.server.ServerWorldProtocol.GateRegisterResponse>(
                serviceImpl, METHODID_REGISTER_GATE)))
        .addMethod(
          METHOD_REGISTER_REGION,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerWorldProtocol.RegionRegisterRequest,
              com.mmo.server.ServerWorldProtocol.RegionRegisterResponse>(
                serviceImpl, METHODID_REGISTER_REGION)))
        .addMethod(
          METHOD_REGISTER_CHARACTER_SERVER,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerWorldProtocol.CharacterRegisterRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_REGISTER_CHARACTER_SERVER)))
        .build();
  }
}
