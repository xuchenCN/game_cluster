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
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.ItemMoveEventRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_MOVE_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "moveEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.ItemCraateEventRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_CREATE_ITEM_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "createItemEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_DESTROY_ITEM_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "destroyItemEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_CREATE_CHARACTER_EVENT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "createCharacterEvent"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest.getDefaultInstance()),
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
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_PLAY_BEGIN_CHANGE_MAP =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "playBeginChangeMap"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.PlayerBeginChangeMapRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest,
      com.mmo.server.CommonProtocol.CommonResponse> METHOD_PLAYER_CHANGE_MAP_COMPLETED =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "EventDispatcher", "PlayerChangeMapCompleted"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.ServerGateProtocol.PlayerChangeMapCompletedRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.mmo.server.CommonProtocol.CommonResponse.getDefaultInstance()));

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
    public void moveEvent(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void createItemEvent(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void destroyItemEvent(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

    /**
     */
    public void createCharacterEvent(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver);

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
  public static abstract class AbstractEventDispatcher implements EventDispatcher, io.grpc.BindableService {

    @java.lang.Override
    public void moveEvent(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MOVE_EVENT, responseObserver);
    }

    @java.lang.Override
    public void createItemEvent(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_ITEM_EVENT, responseObserver);
    }

    @java.lang.Override
    public void destroyItemEvent(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DESTROY_ITEM_EVENT, responseObserver);
    }

    @java.lang.Override
    public void createCharacterEvent(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_CHARACTER_EVENT, responseObserver);
    }

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
      return EventDispatcherGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface EventDispatcherBlockingClient {

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse moveEvent(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse createItemEvent(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse destroyItemEvent(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request);

    /**
     */
    public com.mmo.server.CommonProtocol.CommonResponse createCharacterEvent(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request);

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
  public static interface EventDispatcherFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> moveEvent(
        com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> createItemEvent(
        com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> destroyItemEvent(
        com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> createCharacterEvent(
        com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request);

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
    public void moveEvent(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MOVE_EVENT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void createItemEvent(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_ITEM_EVENT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void destroyItemEvent(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DESTROY_ITEM_EVENT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void createCharacterEvent(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request,
        io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_CHARACTER_EVENT, getCallOptions()), request, responseObserver);
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
    public com.mmo.server.CommonProtocol.CommonResponse moveEvent(com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MOVE_EVENT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse createItemEvent(com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_ITEM_EVENT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse destroyItemEvent(com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DESTROY_ITEM_EVENT, getCallOptions(), request);
    }

    @java.lang.Override
    public com.mmo.server.CommonProtocol.CommonResponse createCharacterEvent(com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_CHARACTER_EVENT, getCallOptions(), request);
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
        com.mmo.server.ServerGateProtocol.ItemMoveEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MOVE_EVENT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> createItemEvent(
        com.mmo.server.ServerGateProtocol.ItemCraateEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_ITEM_EVENT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> destroyItemEvent(
        com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DESTROY_ITEM_EVENT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.mmo.server.CommonProtocol.CommonResponse> createCharacterEvent(
        com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_CHARACTER_EVENT, getCallOptions()), request);
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

  private static final int METHODID_MOVE_EVENT = 0;
  private static final int METHODID_CREATE_ITEM_EVENT = 1;
  private static final int METHODID_DESTROY_ITEM_EVENT = 2;
  private static final int METHODID_CREATE_CHARACTER_EVENT = 3;
  private static final int METHODID_RECEIVE_PING = 4;
  private static final int METHODID_PLAY_BEGIN_CHANGE_MAP = 5;
  private static final int METHODID_PLAYER_CHANGE_MAP_COMPLETED = 6;

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
          serviceImpl.moveEvent((com.mmo.server.ServerGateProtocol.ItemMoveEventRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_CREATE_ITEM_EVENT:
          serviceImpl.createItemEvent((com.mmo.server.ServerGateProtocol.ItemCraateEventRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_DESTROY_ITEM_EVENT:
          serviceImpl.destroyItemEvent((com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
        case METHODID_CREATE_CHARACTER_EVENT:
          serviceImpl.createCharacterEvent((com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest) request,
              (io.grpc.stub.StreamObserver<com.mmo.server.CommonProtocol.CommonResponse>) responseObserver);
          break;
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
      final EventDispatcher serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_MOVE_EVENT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.ItemMoveEventRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_MOVE_EVENT)))
        .addMethod(
          METHOD_CREATE_ITEM_EVENT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.ItemCraateEventRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_CREATE_ITEM_EVENT)))
        .addMethod(
          METHOD_DESTROY_ITEM_EVENT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.ItemDestroyEventRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_DESTROY_ITEM_EVENT)))
        .addMethod(
          METHOD_CREATE_CHARACTER_EVENT,
          asyncUnaryCall(
            new MethodHandlers<
              com.mmo.server.ServerGateProtocol.CharacterCreateEventRequest,
              com.mmo.server.CommonProtocol.CommonResponse>(
                serviceImpl, METHODID_CREATE_CHARACTER_EVENT)))
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
