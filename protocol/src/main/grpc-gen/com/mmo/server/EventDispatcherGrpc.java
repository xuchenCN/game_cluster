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
public class EventDispatcherGrpc {

  private EventDispatcherGrpc() {}

  public static final String SERVICE_NAME = "EventDispatcher";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.CommonProtocol.MoveEvent,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_MOVE_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "moveEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.MoveEvent.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.GateServerPing,
      com.mmo.server.ServerGateProtocol.GateServerPong> METHOD_RECEIVE_PING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "receivePing"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.GateServerPing.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.GateServerPong.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventDispatcherStub newStub(io.grpc.Channel channel) {
    return new EventDispatcherStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventDispatcherBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EventDispatcherBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static EventDispatcherFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EventDispatcherFutureStub(channel);
  }

  /**
   */
  public static interface EventDispatcher {

    /**
     */
    public void moveEvent(com.mmo.server.CommonProtocol.MoveEvent request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractEventDispatcher implements EventDispatcher, io.grpc.BindableService {

    @java.lang.Override
    public void moveEvent(com.mmo.server.CommonProtocol.MoveEvent request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MOVE_EVENT, responseObserver);
    }

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECEIVE_PING, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return EventDispatcherGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface EventDispatcherBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse moveEvent(com.mmo.server.CommonProtocol.MoveEvent request);

    /**
     */
    public com.mmo.server.ServerGateProtocol.GateServerPong receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request);
  }

  /**
   */
  public static interface EventDispatcherFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveEvent(
        com.mmo.server.CommonProtocol.MoveEvent request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.GateServerPong> receivePing(
        com.mmo.server.ServerGateProtocol.GateServerPing request);
  }

  public static class EventDispatcherStub extends io.grpc.stub.AbstractStub<EventDispatcherStub>
      implements EventDispatcher {
    private EventDispatcherStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventDispatcherStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventDispatcherStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventDispatcherStub(channel, callOptions);
    }

    @java.lang.Override
    public void moveEvent(com.mmo.server.CommonProtocol.MoveEvent request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MOVE_EVENT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request,
        io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request, responseObserver);
    }
  }

  public static class EventDispatcherBlockingStub extends io.grpc.stub.AbstractStub<EventDispatcherBlockingStub>
      implements EventDispatcherBlockingClient {
    private EventDispatcherBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventDispatcherBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventDispatcherBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventDispatcherBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse moveEvent(com.mmo.server.CommonProtocol.MoveEvent request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MOVE_EVENT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.ServerGateProtocol.GateServerPong receivePing(com.mmo.server.ServerGateProtocol.GateServerPing request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECEIVE_PING, getCallOptions(), request);
    }
  }

  public static class EventDispatcherFutureStub extends io.grpc.stub.AbstractStub<EventDispatcherFutureStub>
      implements EventDispatcherFutureClient {
    private EventDispatcherFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventDispatcherFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventDispatcherFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventDispatcherFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveEvent(
        com.mmo.server.CommonProtocol.MoveEvent request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MOVE_EVENT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.ServerGateProtocol.GateServerPong> receivePing(
        com.mmo.server.ServerGateProtocol.GateServerPing request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECEIVE_PING, getCallOptions()), request);
    }
  }

  private static final int METHODID_MOVE_EVENT = 0;
  private static final int METHODID_RECEIVE_PING = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventDispatcher serviceImpl;
    private final int methodId;

    public MethodHandlers(EventDispatcher serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MOVE_EVENT:
          serviceImpl.moveEvent((com.mmo.server.CommonProtocol.MoveEvent) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_PING:
          serviceImpl.receivePing((com.mmo.server.ServerGateProtocol.GateServerPing) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.ServerGateProtocol.GateServerPong>) responseObserver);
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
      final EventDispatcher serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_MOVE_EVENT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.CommonProtocol.MoveEvent,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_MOVE_EVENT)))
        .addMethod(
          METHOD_RECEIVE_PING,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.GateServerPing,
              com.mmo.server.ServerGateProtocol.GateServerPong>(
                serviceImpl, METHODID_RECEIVE_PING)))
        .build();
  }
}
