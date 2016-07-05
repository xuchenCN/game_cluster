package org.protocol.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ListenableFuture;
import com.mmo.server.ServerGateProtocol.ChatMsg;
import com.mmo.server.ServerGateProtocol.MasterServer;
import com.mmo.server.ServerGateProtocol.MasterServerId;
import com.mmo.server.ServerGateProtocol.UserLoginRequest;
import com.mmo.server.ServerGateProtocol.UserLoginResponse;
import com.mmo.server.ServerGateServiceGrpc;
import com.mmo.server.ServerGateServiceGrpc.ServerGateServiceBlockingStub;
import com.mmo.server.ServerGateServiceGrpc.ServerGateServiceFutureStub;
import com.mmo.server.ServerGateServiceGrpc.ServerGateServiceStub;

public class SimpleClientTest {
  
  private final ManagedChannel channel;
  private final ServerGateServiceBlockingStub blockingStub;
  private final ServerGateServiceStub asyncStub;
  private final ServerGateServiceFutureStub futureStub;
  
  public SimpleClientTest(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
  }
  
  public SimpleClientTest(ManagedChannelBuilder<?> channelBuilder) {
    channel = channelBuilder.build();
    blockingStub = ServerGateServiceGrpc.newBlockingStub(channel);
    futureStub = ServerGateServiceGrpc.newFutureStub(channel);
    asyncStub = ServerGateServiceGrpc.newStub(channel);
    
  }
  
  public UserLoginResponse login(String uname, String pwd) {
    UserLoginRequest request = UserLoginRequest.newBuilder().setUname(uname).setUpwd(pwd).build();
    return blockingStub.userLogin(request);
  }
  
  public void loginFuture(String uname, String pwd) {
    UserLoginRequest request = UserLoginRequest.newBuilder().setUname(uname).setUpwd(pwd).build();
    final ListenableFuture<UserLoginResponse> future = futureStub.userLogin(request);
    future.addListener(new Runnable() {

      @Override
      public void run() {
        try {
          System.out.println(future.get());
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (ExecutionException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
    }, Executors.newFixedThreadPool(1));
//    return 
  }
  
  public void listStatus(String id) {
    MasterServerId mId = MasterServerId.newBuilder().setId(id).build();
    Iterator<MasterServer> it = blockingStub.listMasters(mId);
    while(it.hasNext()) {
      System.out.println(it.next());
    }
  }
  
  public void status(String[] mIds) throws InterruptedException {
    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<MasterServer> resposeStatus = new StreamObserver<MasterServer>() {

      @Override
      public void onNext(MasterServer value) {
        System.out.println("[cliet-side] [status method] [async]:" + value);
      }

      @Override
      public void onError(Throwable t) {
        System.out.println(t);
        finishLatch.countDown();
      }

      @Override
      public void onCompleted() {
        finishLatch.countDown();
      }
      
    };
    
    StreamObserver<MasterServerId> askIds = asyncStub.status(resposeStatus);
    for(String id : mIds) {
      askIds.onNext(MasterServerId.newBuilder().setId(id).build());
    }
    askIds.onCompleted();
    finishLatch.await(1, TimeUnit.MINUTES);
  }
  
  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
  
  public void chat(String name) throws InterruptedException {
    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<ChatMsg> request = asyncStub.chat(new StreamObserver<ChatMsg>(){

      @Override
      public void onNext(ChatMsg value) {
        System.out.println("[bidirectional] [chat method] [async] " + value.getContant());
      }

      @Override
      public void onError(Throwable t) {
        System.out.println(t);
        finishLatch.countDown();
      }

      @Override
      public void onCompleted() {
        finishLatch.countDown();
      }
    });
    request.onNext(ChatMsg.newBuilder().setContant(name).build());
    request.onCompleted();
    finishLatch.await(1, TimeUnit.MINUTES);
  }

  public static void main(String[] args) throws InterruptedException {
    SimpleClientTest client = new SimpleClientTest("localhost",19999);
    System.out.println(client.login("tester1", "abc").getCode());
    client.loginFuture("tester2", "abc");
    client.listStatus("1");
    client.status(new String[]{"1","2","3"});
    for(int i = 0 ; i < 3; i++) {
      client.chat("tester" + i);
    }
    client.shutdown();
  }

}
