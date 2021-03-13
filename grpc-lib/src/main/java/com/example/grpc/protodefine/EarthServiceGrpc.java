package com.example.grpc.protodefine;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: earthService.proto")
public final class EarthServiceGrpc {

  private EarthServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.protodefine.EarthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest,
      com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.example.grpc.protodefine.DataType.CommonRequest.class,
      responseType = com.example.grpc.protodefine.DataType.CommonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest,
      com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest, com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod;
    if ((getSayHelloMethod = EarthServiceGrpc.getSayHelloMethod) == null) {
      synchronized (EarthServiceGrpc.class) {
        if ((getSayHelloMethod = EarthServiceGrpc.getSayHelloMethod) == null) {
          EarthServiceGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.DataType.CommonRequest, com.example.grpc.protodefine.DataType.CommonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.EarthService", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.DataType.CommonRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.DataType.CommonResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EarthServiceMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EarthServiceStub newStub(io.grpc.Channel channel) {
    return new EarthServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EarthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EarthServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EarthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EarthServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class EarthServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(com.example.grpc.protodefine.DataType.CommonRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.protodefine.DataType.CommonRequest,
                com.example.grpc.protodefine.DataType.CommonResponse>(
                  this, METHODID_SAY_HELLO)))
          .build();
    }
  }

  /**
   */
  public static final class EarthServiceStub extends io.grpc.stub.AbstractStub<EarthServiceStub> {
    private EarthServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EarthServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EarthServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EarthServiceStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(com.example.grpc.protodefine.DataType.CommonRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EarthServiceBlockingStub extends io.grpc.stub.AbstractStub<EarthServiceBlockingStub> {
    private EarthServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EarthServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EarthServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EarthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.protodefine.DataType.CommonResponse sayHello(com.example.grpc.protodefine.DataType.CommonRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EarthServiceFutureStub extends io.grpc.stub.AbstractStub<EarthServiceFutureStub> {
    private EarthServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EarthServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EarthServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EarthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.protodefine.DataType.CommonResponse> sayHello(
        com.example.grpc.protodefine.DataType.CommonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EarthServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EarthServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.example.grpc.protodefine.DataType.CommonRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse>) responseObserver);
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

  private static abstract class EarthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EarthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.protodefine.EarthServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EarthService");
    }
  }

  private static final class EarthServiceFileDescriptorSupplier
      extends EarthServiceBaseDescriptorSupplier {
    EarthServiceFileDescriptorSupplier() {}
  }

  private static final class EarthServiceMethodDescriptorSupplier
      extends EarthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EarthServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EarthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EarthServiceFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
