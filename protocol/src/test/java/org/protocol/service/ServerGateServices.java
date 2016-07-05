package org.protocol.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.HashMap;

import com.mmo.server.ServerGateProtocol.ChatMsg;
import com.mmo.server.ServerGateProtocol.LoginCode;
import com.mmo.server.ServerGateProtocol.MasterServer;
import com.mmo.server.ServerGateProtocol.MasterServerId;
import com.mmo.server.ServerGateProtocol.UserLoginRequest;
import com.mmo.server.ServerGateProtocol.UserLoginResponse;
import com.mmo.server.ServerGateServiceGrpc.AbstractServerGateService;

public class ServerGateServices {

  private int port = 19999;
  private Server server;

  private HashMap<String, String> status = new HashMap<String, String>() {
    {
      put("1", "notbad,good,excellent");
      put("2", "excellent,notbad,good");
      put("3", "good,excellent,notbad");
    }
  };

  public ServerGateServices() {
    server = ServerBuilder.forPort(port).addService(new GateServiceImpl()).build();
  }

  public void startServer() throws IOException, InterruptedException {
    if (server != null) {
      server.start();
      System.out.println("Server started , listening on " + port);
      server.awaitTermination();
    }
  }

  public void stopServer() {
    if (server != null) {
      server.shutdown();
    }
  }

  private class GateServiceImpl extends AbstractServerGateService {

    @Override
    public void userLogin(UserLoginRequest request, StreamObserver<UserLoginResponse> responseObserver) {
      System.out.println(request);
      UserLoginResponse response = UserLoginResponse.newBuilder().setCode(LoginCode.OK).setMsAddr("localhost").setPort(19999).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }

    // server-side
    @Override
    public void listMasters(MasterServerId request, StreamObserver<MasterServer> responseObserver) {
      String stat = status.get(request.getId());
      if (stat != null) {
        String[] arry = stat.split(",");
        for (String s : arry) {
          responseObserver.onNext(MasterServer.newBuilder().setStatus(s).build());
        }
      } else {
        responseObserver.onError(new IOException("invalidate id"));
      }

      responseObserver.onCompleted();
    }

    // client-side
    @Override
    public StreamObserver<MasterServerId> status(final StreamObserver<MasterServer> responseObserver) {
      return new StreamObserver<MasterServerId>() {

        StringBuilder sb = new StringBuilder();

        @Override
        public void onNext(MasterServerId value) {
          String stat = status.get(value.getId());
          if (stat != null) {
            sb.append(stat.split(",")[0]).append("|");
          }
        }

        @Override
        public void onError(Throwable t) {
          System.out.println("Error " + t);
        }

        @Override
        public void onCompleted() {
          responseObserver.onNext(MasterServer.newBuilder().setStatus(sb.toString()).build());
          responseObserver.onCompleted();
        }
      };
    }

    // bidirectional
    @Override
    public StreamObserver<ChatMsg> chat(final StreamObserver<ChatMsg> responseObserver) {
      return new StreamObserver<ChatMsg>() {

        @Override
        public void onNext(ChatMsg value) {
          responseObserver.onNext(ChatMsg.newBuilder().setContant("hello " + value.getContant()).build());
        }

        @Override
        public void onError(Throwable t) {
          System.out.println(t);
        }

        @Override
        public void onCompleted() {
          responseObserver.onCompleted();
        }
        
      };
    }

  }

  public static void main(String[] args) throws Exception {
    new ServerGateServices().startServer();
  }
}
